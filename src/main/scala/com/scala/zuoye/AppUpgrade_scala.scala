package com.ch.dataanalysis.appdata

import scala.collection.immutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by Huge
  * Desc:
  * 2017-08-14,涛哥,共享女友,360应用,北京,v1.0
  * date,name,app,from,version
  * ((),version)
  */

object AppUpgrade_scala {

  def main(args: Array[String]): Unit = {

    /*
     思路：
     1，分割数据，然后使用 元组封装数据
     2，
      */
    val lines: Iterator[String] = Source.fromFile("/Users/zhangjin/myCode/learn/scala-study/app.txt").getLines()

    // 切分数据 并组装成元组
    // 1, 组装成嵌套元组  2，版本号进行处理，去掉前面的v  保留后面的double
    val data: Iterator[((String, String, String, String), Double)] = lines.map(t => {
      val s = t.split(",")
      // 2017-08-14,涛哥,共享女友,360应用,北京,v1.0
      val date = s(0)
      val name = s(1)
      val app = s(2)
      val from = s(3)
      // 对版本号进行特殊处理，获取double类型的版本号
      val version = s(5).substring(1).toDouble // start

      // 封装一个嵌套的元组
      ((date, name, app, from), version)
    })
    // 把迭代器的数据，转换为List，然后调用List上的groupBy方法，进行按照key分组
    val groupedData: Map[(String, String, String, String), List[((String, String, String, String), Double)]] = data.toList.groupBy { case (k, _) => k }

    // 把不需要的数据 去掉
    val groupedData2: Map[(String, String, String, String), List[Double]] = groupedData.mapValues(t => {
      //      t  List[((String, String, String, String), Double)]
      t.map(_._2)
    })

    // 如何获取版本升级的信息
    /**
      * 1, 只有一条版本信息数据
      * 2，有多条，但是版本号是一样的  2.0 2.0
      * 把这些非正常的数据，过滤掉
      */
    val filteredData: Map[(String, String, String, String), List[Double]] = groupedData2.filter {
      case (_, lst) => lst.distinct.length >= 2
    }


    //    filteredData.foreach(println)

    // 第一种方式  利用 zip关联
    // 数据处理，对版本信息的数据进行处理    1.3 1.5 1.6       1.3 -  1.5   1.5 - 1.6
    // 先对版本数据进行排序
    val proData1: Map[(String, String, String, String), List[(Double, Double)]] = filteredData.mapValues(lst => {

      // 首先 对list[Double] 进行排序
      val sortedList: List[Double] = lst.sorted // sortBy(t=>t)  List(1,3,1,5,1.6)   zip  List(1,5,1,6)
      //      lst.sortBy(_)
      // i i + 1
      // 元组
      // 从集合中的第二个元素到最后一个元素
      val subList = sortedList.slice(1, sortedList.length)

      // 组装成成对的版本信息
      val pairVersion: List[(Double, Double)] = sortedList.zip(subList)
      pairVersion
    })

    // 处理并压平
    val result1: immutable.Iterable[((String, String, String, String), Double, Double)] = proData1.flatMap({
      case (keys, lsts) => {
        // 拿每一对版本信息和 原来的keys组装成一个新的元组
        val map: List[((String, String, String, String), Double, Double)] = lsts.map(t => (keys, t._1, t._2))
        map
      }
    })
    //    map1.foreach(println)


    // 第二种方式：利用yield 生成一个新的
    // 对过滤之后的数据进行处理
    val result2: immutable.Iterable[((String, String, String, String), Double, Double)] = filteredData.flatMap { case (k, v) => {
      // 过滤出版本 ，然后进行升序排序
      val lst: List[Double] = v.sortBy(t => t)
      // 获取版本升级信息
      val tuples: immutable.IndexedSeq[(Double, Double)] = for (i <- 0 until lst.length - 1) yield (lst(i), lst(i + 1))

      // 把所有的数据组合起来 然后把IndexedSeq 转换成List
      tuples.map(t => (k, t._1, t._2)).toList
    }
    }


    // 第三种方式  使用 一个临时的集合 来存储数据
    val result3: immutable.Iterable[((String, String, String, String), Double, Double)] = filteredData.flatMap { case (k, v) => {
      // 过滤出版本 ，然后进行升序排序
      val lst: List[Double] = v.sortBy(t => t)

      // 定义个 List缓冲
      val lstTmp = ListBuffer[(Double, Double)]()
      for (i <- 0 until lst.length - 1) {
        // 循环遍历  把元组添加到 在List缓冲中
        lstTmp += ((lst(i), lst(i + 1)))
      }
      // 对lst缓冲进行处理 组装成新的元组
      lstTmp.map(t => (k, t._1, t._2))
    }
    }


    // 排序  整理展示的结果
    val resultFinal = result1.toList.sortBy(t => t._3).map({
      case ((k1, k2, k3, k4), v1, v2) => {
        (k1, k2, k3, k4, "v" + v1, "v" + v2)
      }
    })

    // 打印最终的结果
    resultFinal.foreach(println)


  }
}


