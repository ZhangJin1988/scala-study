package main.scala.com.scala.day4

class Single private{

}


object Single {
  //初始化实例
  var sing:Single = null
  //定义一个apply方法  返回类的实例
  def apply():Single ={
    if(sing == null){
      sing = new Single
    }
      sing
  }

}

object Test2{
  def main(args: Array[String]): Unit = {
    println(Single)
    println(Single)
    println(Single)
    //  val d1 = new Single
  }
}
