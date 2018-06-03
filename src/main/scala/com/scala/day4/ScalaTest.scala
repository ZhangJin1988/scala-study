package com.scala.day4

object ScalaTest {

  def test1: Unit = {

    var flag = true
    var res = 0
    var n = 0
    while (flag) {
      res += n
      n += 1
      if (n == 5) {
        flag = false
        println("退出了 ")
      }
    }

  }

  def add_outer() = {
    var res = 0

    def add_inner() {
      for (i <- 0 until 10) {
        if (i == 5) {
          println("返回了停止了" + res)
          return
        }
        res += i
      }

    }

    add_inner()
    res
  }


  def test2: Int = {
    import scala.util.control.Breaks._
    var res = 0
    breakable {
      for (i <- 0 until 10) {
        if (i == 5) {
          println("停止了 值是:" + res)
          break;
        }
        res += i
      }
    }

    res

  }

  def test3: Unit = {
    val multiDimArr1 = Array.ofDim[Double](3, 4)
    multiDimArr1(0)(0) = 1.0

    val multiDimArr2 = new Array[Array[Int]](3)
    multiDimArr2(0) = new Array[Int](1)
    multiDimArr2(1) = new Array[Int](2)
    multiDimArr2(2) = new Array[Int](3)
    multiDimArr2(1)(1) = 1

    import scala.collection.JavaConversions.bufferAsJavaList
    import scala.collection.mutable.ArrayBuffer
    val command = ArrayBuffer("javac", "C:\\Users\\Administrator\\Desktop\\HelloWorld.java")
    val processBuilder = new ProcessBuilder(command)
    val process = processBuilder.start()
    val res = process.waitFor()
    import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.mutable.Buffer
    val cmd: Buffer[String] = processBuilder.command()
  }


  def test4: Unit = {


    val students = Array("Leo", "Jack", "Jen")
    val scores = Array(80, 100, 90)
    val studentScores = students.zip(scores)
    for ((student, score) <- studentScores)
      println(student + " " + score)
    studentScores.toMap

  }

  def test5: Unit = {

    import scala.collection.JavaConversions.mapAsScalaMap
    val javaScores = new java.util.HashMap[String, Int]()
    javaScores.put("Alice", 10)
    javaScores.put("Bob", 3)
    javaScores.put("Cindy", 8)
    val scalaScores: scala.collection.mutable.Map[String, Int] = javaScores
    import scala.collection.JavaConversions.mapAsJavaMap
    import java.awt.font.TextAttribute._
    val scalaAttrMap = Map(FAMILY -> "Serif", SIZE -> 12)
    val font = new java.awt.Font(scalaAttrMap)
  }


  def test6: Unit = {

    import scala.io.Source
    val source = Source.fromFile("C://Users//Administrator//Desktop//test.txt", "UTF-8")
    val lineIterator = source.getLines
    for (line <- lineIterator) println(line)


//    source = Source.fromFile("C://Users//Administrator//Desktop//test.txt", "UTF-8")
    val lines = source.getLines.toArray
    for (line <- lines) println(line)


//    val source = Source.fromFile("C://Users//Administrator//Desktop//test.txt", "UTF-8")
    val lines2 = source.mkString


    val sources= Source.fromFile("C://Users//Administrator//Desktop//test.txt", "UTF-8")
    for(c <- sources) print(c)


    val sourcees = Source.fromURL("http://www.baidu.com", "UTF-8")
    val sourcees1 = Source.fromString("Hello World")

  }




  def main(args: Array[String]): Unit = {

    test1
    val i: Int = add_outer()
    println("返回值是 : " + i)

    val test: Int = test2
    println("返回值是: " + test)

    test3
    test4
    test5


  }

}
