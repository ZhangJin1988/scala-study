package cn.edu360.day04.casedemo

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object MatchList {

  def main(args: Array[String]): Unit = {
    val lst = List[Int](1,2,3)
    lst match {
//      case List(x,y,z) => println(s"x=$x,y=$y,z=$z")
        // 一个List  =  头元素 +  尾列表
//      case x::y => println(s"x=$x,y=$y")
      case x::y::z ::Nil => println(s"x=$x,y=$y")
      case _ => println("no match ")
    }
  }
}
