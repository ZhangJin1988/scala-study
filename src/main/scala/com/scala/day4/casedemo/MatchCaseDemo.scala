package cn.edu360.day04.casedemo

import scala.util.Random

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc:  匹配样例类  和样例对象
  */

object MatchCaseDemo {

  def main(args: Array[String]): Unit = {

//    Option
    // 把数据封装在数组中
    val prt = Array(Register("xx1"), Register("xx2"), HeartBeat(1000), CheckStatus)

    val index = Random.nextInt(prt.size)

    prt(index) match {

      // 匹配样例类和样例对象
      case Register(par1) => println(par1)

      case HeartBeat(time) => println(time)

      case CheckStatus => println("match case object")

      case _ => println("no match")
    }

  }

}

// 定义样例类 和样例对象
case class Register(id: String)

case class HeartBeat(time: Long)

case object CheckStatus
