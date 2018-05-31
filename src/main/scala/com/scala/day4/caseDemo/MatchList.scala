package main.scala.com.scala.day4.caseDemo

object MatchList {

  def main(args: Array[String]): Unit = {

    val lst = List[Int](1,2,3)

    lst match {
//      case List(x,y,z) =>println(s"x=$x,y=$y,z=$z")
      case x::y =>println(s"x=$x,y=$y")
      case x::y::z::Nil =>println(s"x=$x,y=$y")
      case _ => println("no match")

    }
  }

}
