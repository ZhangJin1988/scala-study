package cn.edu360.day05

import scala.collection.immutable
import scala.io.Source

/**
  * Created by Huge
  * DATE: 2018/6/1
  * Desc:
  * APP
  * 版本升级
  */

object AppUp {

  def main(args: Array[String]): Unit = {
    // 2017-08-15,刘帅老师,斗地主,app store,上海,v2.9
    val lines: Iterator[String] = Source.fromFile("f:/mrdata/appdata/appupgrade.txt").getLines()

    // date  user  App  from version

    //  直接组装成一个元组  嵌套元组
    val splitedData: List[((String, String, String, String), Double)] = lines.map(str => {
      val split = str.split(",")
      val date = split(0)
      val user = split(1)
      val app = split(2)
      val from = split(3)
      // 提取出 DOuble类型的版本
      val version = split(5).substring(1).toDouble

      // 组成嵌套的元组
      ((date, user, app, from), version)
    }).toList


    // 按照指定的条件分组
    val groupedData: Map[(String, String, String, String), List[((String, String, String, String), Double)]] = splitedData.groupBy(_._1)

    // 需求一 ： 求 最大的版本 和 最小的版本
    val result1: Map[(String, String, String, String), (Double, Double)] = groupedData.mapValues(lst => {
      //  同一个key 下的所有的版本
      val newLst: List[Double] = lst.map(_._2).distinct

      // 直接取最大值 和最小值
      (newLst.min, newLst.max)
    })


    // 过滤掉只有一个版本的数据
    val res1Filtered = result1.filter(t => t._2._1 != t._2._2)

//    res1Filtered.foreach(println)


    //  需求2 ： 两两组合  1.0 -  1.3  - 1.5
    // foreach + 临时变量     foreach +  yield

    // zip
    val values: Map[(String, String, String, String), List[(Double, Double)]] = groupedData.mapValues(lst => {
      //  同一个key 下的所有的版本  按照版本的升序排序
      val newLst: List[Double] = lst.map(_._2).distinct.sorted //  1.0 -  1.3  - 1.5  //zip   1.3  - 1.5

      //      newLst.slice(1,newLst.length)

      val version2 = newLst.tail

      // 把版本进行两两关联
      newLst.zip(version2)
    })


    val result2: immutable.Iterable[((String, String, String, String), Double, Double)] = values.flatMap(tp => {
      tp._2.map(oldAndNew => {
        // 把每一个升级前的版本，升级后的版本，和 原有的key组装起来
        (tp._1, oldAndNew._1, oldAndNew._2)
      })
    })
    result2.toList.foreach(println)





  }
}
