package com.scala.day5

object TransferDemo {


  //implicit 关键字修饰的参数 就是 隐式参数
  def maxCrr(x: Int)(implicit y: Int = 20): Int = {
    if (x > y) x else y
  }


  def main(args: Array[String]): Unit = {

    println(maxCrr(10))

  }
}
