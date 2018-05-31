package main.scala.com.actor32.test.scala.day4.caseDemo

object PartitionDemo {

  def meth1(name:String) = name match {
    case "reba" =>100
    case "fengjie" => 90
    case _=> -1
  }

  def pf:PartialFunction[String,Int] = {
    case "reba" =>100
    case "fengjie" => 90
    case _=> -1
  }


  def main(args: Array[String]): Unit = {


    println(meth1("fengjie"))
    println(pf("fengjie"))
  }
}
