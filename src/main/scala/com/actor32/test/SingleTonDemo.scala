package main.scala.com.actor32.test

class SingleTonDemo private{

}


object SingleTonDemo{

  var sing:SingleTonDemo = null

  def apply(): SingleTonDemo = {
    if(sing==null){
      sing = new SingleTonDemo()
    }
    sing
  }

}


object Test2{
  def main(args: Array[String]): Unit = {
    println(SingleTonDemo)
    println(SingleTonDemo)
    println(SingleTonDemo)
    println(SingleTonDemo)
  }
}