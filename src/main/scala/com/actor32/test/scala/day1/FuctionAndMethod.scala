package main.scala.com.actor32.test.scala.day1

object FuctionAndMethod {

  val f2 = (x:Int,y:Int) => x*y*1.0
  //函数作为方法的参数
  def add2(x:Int ,y:Int,f:(Int,Int)=>Double)={

    f(x,y)

  }

  def add() = (x:Int,y:Int) => x+y


  def main(args: Array[String]): Unit = {

    val function = add()

    println(function(1,3));

    println(add2(10,20,(a1:Int,a2:Int) => a1 + a2));


    println(add2(10,20,f2));
  }

}
