package cn.edu360.day04.casedemo

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object MatchTuple {

  def getValues(arr: Array[Int]): (Int, Int) = {
    (arr.max, arr.min)
  }

  def main(args: Array[String]): Unit = {

    // 两种接收方式
    val (max, min) = getValues(Array(1, 3, 4))
    val tp2 = getValues(Array(1, 3, 4))

    val tp = (1, 9.9, true)

    tp match {
      case (x, y, z) => println(s"x=$x,y=$y,z=$z")
      case (x, _, _) => println(x)
      case _ => println("no match")
    }

  }
}
