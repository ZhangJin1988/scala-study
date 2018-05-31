package main.scala.com.actor32.test.scala.day3

object Practice {

  //  5，求平均值
 //    var d1 = Array(("bj",28.1),("sh",28.7),("gz",32.0),("sz",33.1)
  //  6，key value 互换
  //  val lst = List("Id1-The Spark","Id2-The Hadoop","Id3-The Spark")
  //  结果值:
  //    The-Id1 Id2 Id3
  //  Spark-Id1 Id3
  //    Hadoop-Id2


  def main(args: Array[String]): Unit = {
    val lst = List("Id1-The Spark", "Id2-The Hadoop", "Id3-The Spark")
    val strings: List[String] = lst.map(tp => tp.split(" ").reverse.mkString(" "))
    strings.foreach(println)


    val mp = Map[String,Int]("a"->1001,"b"->1010,"c"->1111)
    val stringToInt: Map[String, Int] = mp.mapValues(b=>b*10)
    val keys: Iterable[String] = stringToInt.keys
    keys.foreach(print)

    mp.mapValues(b=>b*10).foreach(print)
//    for(i->keys){
//
//    }

    var arr = Array[Int](11,12,34)
    val ints: Array[Int] = arr.take(2)
    println(ints.toList)
  }
}
