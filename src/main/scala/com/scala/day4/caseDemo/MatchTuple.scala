package main.scala.com.scala.day4.caseDemo

object MatchTuple {


  def getValues(arr:Array[Int]):(Int,Int) ={
    (arr.max,arr.min)
  }
  def main(args: Array[String]): Unit = {
    val tp = (1,9.9,true)

    tp match {
      case (x,y,z) => println(s"x=$x,y=$y,z=$z")
//      case (x,y) => println(s"x=$x,y=$y")
      case _ => println("no match")
    }
  }

}
