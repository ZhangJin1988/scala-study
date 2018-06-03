package com.scala.day4

;

import scala.collection.mutable.ArrayBuffer;




class Class2 {

  class Student(val name: String)

  val students = new ArrayBuffer[Class2#Student]

  def register(name: String) = {
    new Student(name)
  }

}

object Class2 {
  def main(args: Array[String]): Unit = {
    val c1 = new Class2
    val leo = c1.register("leo")
    c1.students += leo
    val c2 = new Class2
    val jack = c2.register("jack")
    c1.students += jack
  }
}


//class Class2(val name: String) { outer => class Student(val name: String) {
//        def introduceMyself = "Hello, I'm " + name + ", I'm very happy to join class " + outer.name }
//        def register(name: String) = { new Student(name)
//        } }
//

