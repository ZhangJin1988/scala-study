package main.scala.com.actor32.test.scala.day1

object WhileDemo {

  def main(args: Array[String]): Unit = {

    var i = 0;

    var flag = true;
    while (i < 100 && flag) {
      if (i == 5) {
        flag = false;
      }

      println("hello scala")
      i += 1;


    }
  }

}
