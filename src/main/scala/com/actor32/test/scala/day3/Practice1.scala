package main.scala.com.actor32.test.scala.day3

import scala.collection.mutable.ArrayBuffer

object Practice1 {

  def main(args: Array[String]): Unit = {

    import scala.io.Source
    val lines: Iterator[String] = Source.fromFile("/Users/zhangjin/myCode/learn/scala-study/pvuv.txt").getLines()

    val list = lines.toList

    var array = ArrayBuffer[(String, String, String, String)]();
    for (i <- 0 to list.length - 1) {

      val str = list(i).toString
      val strs: Array[String] = str.split(",")
      var tempTup = ();

      //1 过滤脏数据 获取有效数据 元组  S 页面 U 用户 D 天 H 小时（带天） 然后 装入 List
      if (strs != null && strs.length == 3 && strs(0) != "" && strs(1) != "" && strs(2) != "") {
        array ++= List((strs(0), strs(1), strs(2).split(" ")(0), strs(2).split(":")(0)))
      }
    }

    //2 第一层分组 按照页面分组

    val siteMaps: Map[String, ArrayBuffer[Tuple4[String, String, String, String]]] = array.groupBy(tp => tp._1)


    //3 第二层分组 按照天分组  这个每个values 是 按照每个页面来统计的 我只要拿到每天的 就是 pv
    val dayResults: Map[String, Map[String, ArrayBuffer[(String, String, String, String)]]] = siteMaps.mapValues(a => {
      val dayMaps: Map[String, ArrayBuffer[(String, String, String, String)]] = a.groupBy(tp => tp._3)
      dayMaps
    })


    //4 直接累加 遍历打印 就是 日pv
    var arryDayPv: ArrayBuffer[(String, String, String)] = ArrayBuffer();
    var arryDayUv: ArrayBuffer[(String, String, String)] = ArrayBuffer();
    var arryHourPv: ArrayBuffer[(String, String, String)] = ArrayBuffer();
    var arryHourUv: ArrayBuffer[(String, String, String)] = ArrayBuffer();
    for ((k, v) <- dayResults) {
      for ((key, value) <- v) {
        //        print("页面是" + " " + k + " ")
        //        print("日期" + key + "的PV是" + value.size)
        //        println()
        arryDayPv.+=:(k, key, value.size.toString)
      }


    }

    //5 去重用户之后再累加 就是 UV
    for ((k, v) <- dayResults) {
      for ((key, value) <- v) {
        val dayUserMaps: Map[String, ArrayBuffer[(String, String, String, String)]] = value.groupBy(tp => tp._2)
        //        print("页面是" + " " + k + " ")
        //        print("日期" + key + "的UV是" + dayUserMaps.size)
        //        println()
        arryDayUv.+=:(k, key, dayUserMaps.size.toString)

      }
    }


    //6 回到第一层分组 按小时分组 重复 3 4 5 按照小时分组

    val dayResults2: Map[String, Map[String, ArrayBuffer[(String, String, String, String)]]] = siteMaps.mapValues(a => {
      val dayMaps: Map[String, ArrayBuffer[(String, String, String, String)]] = a.groupBy(tp => tp._4)
      dayMaps
    })

    for ((k, v) <- dayResults2) {
      for ((key, value) <- v) {
        //        print("页面是" + " " + k + " ")
        //        print("小时" + key + "的PV是" + value.size)
        //        println()
        arryHourPv.+=:(k, key, value.size.toString)

      }
    }

    for ((k, v) <- dayResults2) {
      for ((key, value) <- v) {
        val dayUserMaps2: Map[String, ArrayBuffer[(String, String, String, String)]] = value.groupBy(tp => tp._2)
        //        print("页面是" + " " + k + " ")
        //        print("日期" + key + "的UV是" + dayUserMaps2.size)
        arryHourUv.+=:(k, key, dayUserMaps2.size.toString)

        //        println()
      }
    }


    println("--------- 日PV")
    arryDayPv.sortBy(_._1).foreach(println)

    println("--------- 日UV")
    arryDayUv.sortBy(_._1).foreach(println)

    println("--------- 小时PV")
    arryHourPv.sortBy(_._1).foreach(println)

    println("--------- 小时UV")
    arryHourUv.sortBy(_._1).foreach(println)


  }

}
