package com.ch.scala.demo

import java.io.{File, PrintWriter}

import com.alibaba.fastjson.JSONObject

import scala.collection.immutable
import scala.io.Source

/**
  * Created by Huge
  * Desc:
  * 电影 数据分析
  */

object MovieAnalysis {
  // 取前5个
  val topN = 5
  /*
  读取json的API非常多，这里只提供 fastjson来读取json文件
  需要导入fastjson的依赖jar包
              <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>fastjson</artifactId>
              <version>1.2.12</version>
          </dependency>
   */

  def main(args: Array[String]): Unit = {


    val lines = Source.fromFile("/Users/zhangjin/myCode/learn/scala-study/rating.json").getLines()

    // 使用jsonAPI来读取json格式的数据
    //    readDataFromJsonAPI(lines)

    // 使用 普通文本的方式来读取json个数的数据
    val data: Iterator[(String, Double, String)] = getData(lines)

    // 过滤掉非法数据  防止迭代器中数据被访问完，先把迭代器转换成List集合
    val filterData: List[(String, Double, String)] = data.filter(_._2 != -1.0).toList

    // 按照用户分组进行统计
    val groupedByUserData: Map[String, List[(String, Double, String)]] = filterData.groupBy(_._3)

    println("------每个用户评分最高的N条记录------------")
    getMaxRatingPerUser(groupedByUserData)

    println("------每个用户评分的平均值------------")
    val avg: Map[String, Double] = getAvgRatingPerUser(groupedByUserData)

    println("------最大方（评分平均值高）的N个用户------------")
    //    最大方（评分平均值高）的N个用户    就是对 用户评分的平均值  求topN
    //    avg.toList.sortBy(-_._2).take(topN).foreach(println)
    printTopN(avg, topN)

    // 按照电影排序
    val groupByMovieData: Map[String, List[(String, Double, String)]] = filterData.groupBy(_._1)

    println("------最热门的N部电影------------")
    getHotingMovieTopN(groupByMovieData)

    println("------评价最高的N部电影------------")
    getMaxRatingMovieTopN(groupByMovieData)
  }


  private def getData(lines: Iterator[String]) = {
    val data /*: Iterator[(String, Double, String)] */ = lines.map(t => {
      // 使用正则把 " {  }  这三个字符替换掉
      val reg = t.replaceAll("[\"|{|}]", "")
      val split = reg.split(",")
      val movie = split(0).split(":")(1)
      val rate = split(1).split(":")(1)
      val uid = split(3).split(":")(1)

      // 设置评分值为 -1.0
      var rateDouble = -1.0
      try {
        // 如果能转换  就赋予新的值
        rateDouble = rate.toDouble
      } catch {
        // 如果捕获异常，就使用默认值 -1.0
        case e: Exception =>
      }
      (movie, rateDouble, uid)
    })
    data
  }

  /*
        // 使用jsonAPI来读取json格式的数据
     */
  private def readDataFromJsonAPI(lines: Iterator[String]) = {

    val readJsonData: Iterator[(String, String, String)] = lines.map(t => {
      // 把每一行数据 转换为JSONObject，然后获取每一个可用的值
      val pj: JSONObject = com.alibaba.fastjson.JSON.parseObject(t)
      val movie = pj.getString("movie")
      val rate = pj.getString("rate")
      val uid = pj.getString("uid")
      (movie, rate, uid)
    })
  }

  private def getMaxRatingMovieTopN(groupByMovieData: Map[String, List[(String, Double, String)]]) = {
    //    评价最高的N部电影 平均分最高
    val stringToDouble: Map[String, Double] = groupByMovieData.map(t => {
      val totalRate = t._2.map(_._2).sum
      val len = t._2.length
      // 平均分
      (t._1, totalRate / len)
    })
    stringToDouble.toList.sortBy(-_._2).take(topN).foreach(println)
    //    printTopN(stringToDouble,topN)
  }

  private def getHotingMovieTopN(groupByMovieData: Map[String, List[(String, Double, String)]]) = {
    // 最热门的N部电影 评分次数最多
    val stringToInt: Map[String, Int] = groupByMovieData.map(t => {
      (t._1, t._2.length)
    })
    stringToInt.toList.sortBy(-_._2).take(topN).foreach(println)
  }

  private def getAvgRatingPerUser(groupedByUserData: Map[String, List[(String, Double, String)]]) = {
    // 每个用户评分的平均值
    val avg: Map[String, Double] = groupedByUserData.mapValues(t => {
      // 评分的值
      val totalRate = t.map(_._2).sum
      val len = t.length
      // 求平均值
      (totalRate.toDouble / len)
    })
    avg.foreach(println)
    avg
  }

  private def getMaxRatingPerUser(groupedByUserData: Map[String, List[(String, Double, String)]]) = {
    // 每个用户评分最高的N条记录
    // 按照评分的降序排序,取 top5
    val values: Map[String, List[(String, Double, String)]] = groupedByUserData.mapValues(t => t.sortBy(-_._2).take(topN))
    values.foreach(println)
  }

  // 定义一个方法 打印topN的结果值
  def printTopN(mp: Map[String, Double], topN: Int) = {
    mp.toList.sortBy(-_._2).take(topN).foreach(println)
  }
}
