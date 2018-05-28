package com.scala.day1

object Test {

  def mx(num:Int,sum:Int):Int = {
    if(num<=10)return mx(num+1,sum+num)
    else return sum
  }


  def main(args: Array[String]): Unit = {
    var num = 0
    var x = 0
    while(x<=10){
      num += x
      x +=1
    }

    val unit: Unit = println(num)
    println(x)
  }

}
