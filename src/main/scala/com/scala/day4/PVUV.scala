package cn.edu360.day04

import scala.io.Source

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object PVUV {

  def main(args: Array[String]): Unit = {
    // jd  user1 2018-05-30 9:00
    // jd  user1 2018-05-30 10:00
    // jd  user1 2018-05-30 10:01
    // jd  user2 2018-05-30 10:00
    // jd  user2 2018-05-30 10:00

    // jd  pv   5   uv: 2   正常情况下  PV UV 都是按照天

    // 按小时的  9:00 -- 9:59   10  10:00 - 10:59
    // 9 点 pv： 1 uv 1
    // 10    pv: 4   uv  2


    // 1 读取数据
    val lines: Iterator[String] = Source.fromFile("f:/mrdata/pvuv/pvuv.txt").getLines()

    //    lines.foreach(println)


    // 2 把不完整的数据过滤掉  数据切分    提取哪些数据：site  user day  hour
    val splitArr: Iterator[Array[String]] = lines.map(str => {
      // + 匹配多个
      str.split(",+")
    })

    // 2 个 filter  不要连续用多个filter 重量级的算子
    val filteredData: Iterator[Array[String]] = splitArr.filter(arr => {
      arr.length == 3
    })

    // 提取出有用字段
    val datas: List[(String, String, String, Int)] = filteredData.map(arr => {
      val site = arr(0)
      val user = arr(1)
      val dayAndHour = arr(2).split(" ")
      val day = dayAndHour(0) // 2018-03-08
      val hour = dayAndHour(1).split(":")(0).toInt // 取小时
      (site, user, day, hour)
    }).toList



    //  3 提取数据 --- 分组 统计 pv  uv
    // 统计 天的PV UV
    val PVUVData: Map[(String, String), List[(String, String, String, Int)]] = datas.groupBy(t => (t._1, t._3))

    val dayRes: Map[(String, String), (Int, Int)] = PVUVData.mapValues(lst => {
      // 天的pv
      val pv = lst.size

      // 取用户
      val uv = lst.map(_._2).distinct.size

      // 把天的pv 和uv  返回
      (pv, uv)
    })

    dayRes.foreach(println)


    val HourPVUVData: Map[(String, String, Int), List[(String, String, String, Int)]] = datas.groupBy(t => (t._1, t._3,t._4))

    val hourRes: Map[(String, String, Int), (Int, Int)] = HourPVUVData.mapValues(lst => {
      val hpv = lst.size
      val huv = lst.map(_._2).toSet.size
      (hpv, huv)
    })

    hourRes.foreach(println)

  }

}
