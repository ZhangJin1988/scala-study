package main.scala.com.actor32.test.scala.day1

import scala.io.StdIn

object KeyTestDemo {

  //猜数字
  //从键盘获取一个数字 给定一个数字100 做一个判断 直到猜对 程序终止

  //  do{
  //
  //  }while(){
  //
  //}

  def main(args: Array[String]): Unit = {

    var flag = true;
    var line = 0;
    do {
      print("控制台输入请输入")
      line = StdIn.readLine().toInt;
      if (line == 10) {
        println("对了")
        flag = false
      } else if (line > 10) {
        println("大了")
      } else {
        println("小了")
      }

    } while (flag )


  }

}
