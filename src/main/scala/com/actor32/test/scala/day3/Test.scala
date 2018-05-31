package main.scala.com.actor32.test.scala.day3

import scala.collection.mutable.ArrayBuffer

object Test {
  def main(args: Array[String]): Unit = {

    val a = ArrayBuffer[Int]()
    a += (1, 2, 3, 4, 5, -1, -3, -5, -9)

    var foundFirstNegative = false
    val keepIndexes = for (i <- 0 until a.length if !foundFirstNegative || a(i) >= 0) yield {
      if (a(i) < 0) foundFirstNegative = true
      i }
    for (i <- 0 until keepIndexes.length) { a(i) = a(keepIndexes(i)) }
    a.trimEnd(a.length - keepIndexes.length)

    a.foreach(println)


  }

}
