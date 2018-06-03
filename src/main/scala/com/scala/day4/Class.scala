package com.scala.day4


import scala.collection.mutable.ArrayBuffer

class Class {

  class Student(val name: String)

  val students = new ArrayBuffer[Student]

  def register(name: String) = {
    new Student(name)
  }
}


object Class {

//  class Student(val name: String)

  def main(args: Array[String]): Unit = {
    val c1 = new Class
    val leo = c1.register("leo")
    c1.students += leo
    val c2 = new Class
    val jack = c2.register("jack")
//    c1.students += jack

  }

}
