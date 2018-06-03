package com.scala.day4

import scala.util.Random

class Test5 {

//  def main(args: Array[String]): Unit = {
//    val arr = Array("YoshizawaAkiho", "YuiHatano", "AoiSola")
//    //    e
//    val index = Random.nextInt(arr.length) // 0 // match +   case
//    arr(index) match {
//      case "YuiHatano" => println("match xx  ")
//      case "AoiSola" => println("oo  ")
//      //                   case _ => println( "no match xxx ") }
//      //        1
//      //        2
//
//
//    }
//
//  }


}

object test5{
  def main(args: Array[String]): Unit = {

    val arr = Array("YoshizawaAkiho", "YuiHatano", "AoiSola")
    //    e
    val index = Random.nextInt(arr.length) // 0 // match +   case
    arr(index) match {
      case "YuiHatano" => println("match xx  ")
      case "AoiSola" => println("oo  ")
      //                   case _ => println( "no match xxx ") }
      //        1
      //        2


    }
  }
}
