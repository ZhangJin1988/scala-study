package main.scala.com.actor32.test.scala.day3

object AvgTest {


  // var d1 = Array(("bj",28.1),("sh",28.7),("gz",32.0),("sz",33.1)


  def main(args: Array[String]): Unit = {
    var d1 = Array(("bj", 28.1), ("sh", 28.7), ("gz", 32.0), ("sz", 33.1))
    var d2 = Array(("bj", 27.3), ("sh", 30.1), ("gz", 33.3))
    var d3 = Array(("bj", 28.1), ("sh", 28.7), ("gz", 32.0), ("sz", 30.5))
    val tuples: Array[(String, Double)] = d1 ++ d2 ++ d3
    val cityMap: Map[String, Array[(String, Double)]] = tuples.groupBy(_._1)
    val result: Map[String, Double] = cityMap.mapValues(a => {
      val months: Int = a.length;
      val totalTemp: Double = a.map(m => m._2).sum
      totalTemp / months
    })
    result.foreach(print)


  }

}
