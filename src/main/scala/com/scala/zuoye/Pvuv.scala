package com.scala.zuoye

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.io.Source

object Pvuv {

  def main(args: Array[String]): Unit = {
    var lines: Iterator[String] = Source.fromFile("/Users/zhangjin/myCode/learn/scala-study/pvuv.txt").getLines()


    val splitArr: Iterator[Array[String]] = lines.map(str => {
      str.split(",+")
    })
    val filteredData: Iterator[Array[String]] = splitArr.filter(arr => {
      arr.length == 3
    })
    val datas: List[(String, String, String, Int)] = filteredData.map(arr => {
      val site = arr(0)
      val user = arr(1)
      val dayAndHour = arr(2).split(" ")
      val day = dayAndHour(0)
      val hour = dayAndHour(1).split(":")(0).toInt
      (site, user, day, hour)
    }).toList

    val PVUVDATA: Map[(String, String), List[(String, String, String, Int)]] = datas.groupBy(t => (t._1, t._3))

    val dayRes: Map[(String, String), (Int, Int)] = PVUVDATA.mapValues(lst => {
      val pv = lst.size
      val uv = lst.map(_._2).distinct.size
      (pv, uv)
    })
    println("日pvuv")
    dayRes.foreach(println)


    val HourPvuvdata: Map[(String, String, Int), List[(String, String, String, Int)]] = datas.groupBy(t => (t._1, t._3, t._4))
    val hourRes: Map[(String, String, Int), (Int, Int)] = HourPvuvdata.mapValues(lst => {
      val hpv = lst.size
      val huv = lst.map(_._2).toSet.size
      (hpv, huv)
    })
    println("每小时pvuv")
    hourRes.foreach(println)

    //统计7天内的日期  重新处理 datas 按照周来分组 7天一组

    val weekDatas: List[(String, String, String, String)] = datas.sortBy(_._3).map(t => {
      var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val calendar: Calendar = Calendar.getInstance()
      calendar.setTime(dateFormat.parse(t._3))
      val week = calendar.get(Calendar.WEEK_OF_YEAR)
      calendar.set(Calendar.WEEK_OF_YEAR, week)
      calendar.set(Calendar.DAY_OF_WEEK, 2)
      val weekDate:String = dateFormat.format(calendar.getTime)
      (t._1, t._2, t._3, weekDate)
    })
    val WeekPVUVDatas: Map[(String, String), List[(String, String, String, String)]] = weekDatas.groupBy(t=>(t._1,t._4))

    val weekRes: Map[(String, String), (Int, Int)] = WeekPVUVDatas.mapValues(lst => {
      val pv = lst.size
      val uv = lst.map(_._2).distinct.size
//      val day = lst(1)._3
      (pv, uv)
    })
    println("周pv uv")
    weekRes.foreach(println)

  }

}
