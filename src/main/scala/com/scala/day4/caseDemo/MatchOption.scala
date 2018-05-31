package main.scala.com.scala.day4.caseDemo

object MatchOption {
  /**
    * Option类有两个子类，一个是Some样例类 一个是None 样例对象
    */




  def main(args: Array[String]): Unit = {
    val mp = Map[String,Int]("a1"->100,"a2"->2000)

    val mayBeInt : Option[Int] = mp.get("a2")

    //模式匹配 有返回值
    val unit: Int = mayBeInt match {
      case Some(v) => v
      case None => -1
    }

    println(unit)
  }

}
