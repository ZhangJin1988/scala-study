package com.scala.day4

import akka.japi.pf.Match

import scala.util.Random

object Test6 {
  def main(args: Array[String]): Unit = {

//    val MatchTypes: Match = new Match
    val arr = Array[Any](10,9.9,true,"error","MatchTypes")
    val index = Random.nextInt(arr.length)
    println(s"index=${index}")
    arr(index) match {
      //
      case x:Int if(x>5) => println(x)
      case y:Double => println(y)
      case z:String => println(z)
      case b:Boolean => println(b)
      case _ => println("no match")
    }

  }

}
