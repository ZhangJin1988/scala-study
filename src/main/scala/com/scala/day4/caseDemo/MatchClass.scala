package main.scala.com.scala.day4.caseDemo

import scala.util.Random

object MatchClass {


  def main(args: Array[String]): Unit = {
    //把数据封装在数组中
    val prt = Array(Register("xx1"),Register("xx2"),HeartBeat(1000),CheckStatus)

    var index = Random.nextInt(prt.length)

    prt(index) match {

      case  Register(par1) => println(par1)
      case  HeartBeat(time) => println(time)
      case  CheckStatus => println("match case object")
      case _=> println("no match")
    }
  }

}
case class Register(id:String)
case class HeartBeat(time:Long)

case object CheckStatus