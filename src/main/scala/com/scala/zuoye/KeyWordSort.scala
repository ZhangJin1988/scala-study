package com.scala.zuoye

import scala.io.Source

object KeyWordSort {

  def main(args: Array[String]): Unit = {
    val lines: Iterator[String] = Source.fromFile("/Users/zhangjin/myCode/learn/scala-study/impclick.txt").getLines()

    //第一步  拿到list 里面嵌套 tuple

    val datas: List[(String, String, String, String)] = lines.flatMap(s => {
      val strs: Array[String] = s.split(",")
      val words: Array[String] = strs(1).replace("|", ",").split(",")
      strs(1).split(",")(1).toDouble
      var lst = scala.collection.mutable.ArrayBuffer[(String, String, String, String)]()
      for (index <- 0 until words.length) {
        val tp: (String, String, String, String) = (strs(0), words(index), strs(2), strs(3))
        lst += tp
      }
      lst
    }).toList

    //第二步 直接分组
    val wordDatas: Map[(String, String), List[(String, String, String, String)]] = datas.groupBy(tp => (tp._1, tp._2))


    //第三步 累加
    val result: Map[(String, String), (Int, Int)] = wordDatas.mapValues(value => {
      var sumShow = 0;
      var sumEnter = 0;
      for (v <- value) {
        sumShow = sumShow + v._3.toInt
        sumEnter = sumEnter + v._4.toInt
      }
      (sumShow, sumEnter)
    })
    result.foreach(println)

    //    datas.foreach(println)


  }

}
