package cn.edu360.day04.casedemo

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object MatchOption {

  def main(args: Array[String]): Unit = {
    val mp = Map[String,Int]("a1"->100,"a2"-> 2000)

    val maybeInt: Option[Int] = mp.get("a1111")

    // 模式匹配 是有返回值的
    val result: Int = maybeInt match {
      case Some(v) => v
      case None => -1
    }
    println(result)

    mp.getOrElse("a1111",-1)
  }
}
