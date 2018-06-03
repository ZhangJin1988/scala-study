package cn.edu360.day04

/**
  * Created by Huge
  * Desc:
  * 求平均温度
  */

object TempDemo {

  def main(args: Array[String]): Unit = {
    // 练习 求平均值
    val d1 = Array(("bj", 28.1), ("sh", 28.7), ("gz", 32.0), ("sz", 33.1))
    val d2 = Array(("bj", 27.3), ("sh", 30.1), ("gz", 33.3))
    val d3 = Array(("bj", 28.2), ("sh", 29.1), ("gz", 32.0), ("sz", 30.5))
    val tuples: Array[(String, Double)] = d1++d2++d3
    val by: Map[String, Array[(String, Double)]] = tuples.groupBy(_._1)
    val values: Map[String, Double] = by.mapValues(kv => {
      val sum: Double = kv.map(_._2).sum
      val length: Int = kv.length
      sum / length

    })
    values.toList.sortBy(-_._2).foreach(println)
  }
}
