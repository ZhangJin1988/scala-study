package main.scala.com.actor32.test.scala.day1

/**
  * desc:第一个scala程序 hello scala
  */
object HelloScala {
  def main(args: Array[String]): Unit = {

    var age:Int = 20
    var result = if(age<25){
      age
    }


    println(result)
//    result = if(age<25){
//      age
//    }else{
//      ()
//    }

    println(age)

    println("hello scala")
  }

}
