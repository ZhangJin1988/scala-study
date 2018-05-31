package main.scala.com.actor32.test.scala.day2

object MethodTest {

  def main(args: Array[String]): Unit = {

    var arr = Array[Int](1,3,5,7)

    arr.foreach(println _ )

    arr.foreach(println)
  }

}
