package com.scala.day4

import scala.collection.mutable.ArrayBuffer

object Class1 {

  class Student(val name: String)

  def main(args: Array[String]): Unit = {


    val c1 = new Class1
    val leo = c1.register("leo")
    c1.students += leo
    val c2 = new Class1
    val jack = c2.register("jack")
    c1.students += jack

  }

}

class Class1 {
  val students = new ArrayBuffer[Class1.Student]
  def register(name: String) = {
    new Class1.Student(name)
  }


}

