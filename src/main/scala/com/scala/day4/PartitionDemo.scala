package cn.edu360.day04

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object PartitionDemo {

  // 普通的方法  一个输入参数 一个返回值类型
  def meth1(name: String) = name match {
    case "reba" => 100
    case "fengjie" => 90
    case _ => -1
  }
  // 可以利用偏函数来实现   省略了match
  def pf:PartialFunction[String,Int]={
    case "reba" => 100
    case "fengjie" => 90
    case _ => -1
  }

  def pf2:PartialFunction[Any,Int]={
    case x:Int => x * 10
    case _ => -1
  }

  def main(args: Array[String]): Unit = {



    println(meth1("reba"))
    println(meth1("fengjie"))

    println(pf("reba"))

    val arr = Array[Any](1,3,4,"error")

    arr.map(pf2).foreach(println)

    // 有一些方法，指定必须是偏函数类型
    arr.collect(pf2).foreach(println)
  }
}
