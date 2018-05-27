package com.scala.day1

object ForTest2 {
  def main(args: Array[String]): Unit = {

    var arr = Array[Int](1,2,3,4,5,6,7,8,9,10)

    for(a <- arr if(a%2==0)){
      println(a)
    }


    for(i<-1 to 3){
      for(j<-1 to 3){
        if(i!=j){
          print(i*10 + j + " ")
        }
      }
    }
    println("---------")


    for(i<-1 to 3;j<-1 to 3 if(i!=j)){
      print(i*10 + j+" ")
    }


    //正常的for循环的返回值是Unit
    //yield 推导式 数组中的每一个元素乘以10
    var result:Array[Int] = for(a<-arr) yield a*10

    println(result.toBuffer)

    //普通的写法
    //函数式编程的写法
    arr.filter(t=>t%2 ==0).foreach(println);
    arr.map(t => t*10).foreach(println);
  }

}
