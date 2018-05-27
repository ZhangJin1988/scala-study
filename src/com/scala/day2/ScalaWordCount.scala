package com.scala.day2

object ScalaWordCount {

  def main(args: Array[String]): Unit = {


    val lst = List[String]("hellow word hello tom tom jim","hello spark scala")


    //单词计数思路

    //1，切分数据
    val spited :List[Array[String]] = lst.map(str => str.split(" "));

    val list = spited.flatten
    //2，组装 单词和1组装
//    list.map(t=>)

    //3，分组

    //4，聚合 统计

  }

}
