package main.scala.com.actor32.test.scala.day1

object MethodDeom {


  def main(args: Array[String]): Unit = {


    val result = add(2, 3)
    println(result)

    add10()
    add11
    val i = addMul(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(i)
    val j = addMul2(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(j)
  }


  def addMul2(x:Int*):Int = {
    x.sum
  }


  def addMul(x: Int*): Int = {
    var sum = 0;

    for (i <- x) {
      sum += i;
    }
    sum;
  }


  def add10(): Int = {
    100
  }

  def add11: Int = {
    100
  }


  //两个int 类型的参数求和 和也返回int 类型
  def add(a: Int, b: Int) = {
    a + b
  }

}
