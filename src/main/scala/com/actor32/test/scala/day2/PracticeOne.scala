package main.scala.com.actor32.test.scala.day2

object PracticeOne {

  def getAvg(array: Array[Double]): Double = {
    return array.sum / array.length
  }

  def getTup(array: Array[Double]): (Double, Double) = {
    return (array.max, array.min);
  }

  def getValues(values: Array[Int], v: Int): (Int, Int, Int) = {
    //    return (values.filter(_ > v).length, values.filter(_ == v).length, values.filter(_ < v).length);
    return (values.count(_ > v), values.count(_ == v), values.count(_ < v))
  }

  def getReverse1(values: Array[Int]): Array[Int] = {

    //思路 1 步长 分组 和 下一个交换
    //    values.foreach(1 to values.length by 2)

    for (a <- 0 to values.length - 2 by 2) {
        var temp = values(a+1);
        values(a+1) = values(a);
        values(a) = temp
    }
    values

    //
    //    })


  }

  def main(args: Array[String]): Unit = {
    //    val arry = Array[Double](3,5,8,10,12)
    //    println(getAvg(arry))
    //    println(getTup(arry))


    val array1 = Array[Int](23, 3434, 3434, 3434343, 23242, 23423)

    getReverse1(array1).foreach(println)
    //    array1.zipWithIndex()

    val res = getValues(array1, 4000)

    println(res)


    var arr = Array[String]("hello world", "hello tom", "hello scala")


    val stringToInt = arr.flatMap(t => t.split(" ")).groupBy(key => key).map(a => (a._1, a._2.length))

    println(stringToInt)

  }

}
