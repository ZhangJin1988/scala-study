package com.scala.day2

object PracticeTwo {

  def getTup(array:Array[Int] ) :Any  = {

    return (array.max,array.min);

  }


  def main(args: Array[String]): Unit = {





    var array = Array(1,2,3,8)



    println(getTup(array))

  }

}
