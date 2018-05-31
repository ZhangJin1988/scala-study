package main.scala.com.actor32.test.scala.day1

class ForDemo {
  def main(args: Array[String]): Unit = {

    var arr = Array[Int](1,2,3,4,5,6,7,8,9,10)

    for(a<- arr){
      println(a)
    }

    for(i<-0 to arr.length-1){
      println(i+"--"+arr(i))
    }
  }

}
