package com.scala.day4


class Class3(val name: String) {
  outer =>

  class Student(val name: String) {
    def introduceMyself = "Hello, I'm " + name + ", I'm very happy to join class " + outer.name
  }

  def register(name: String) = {
    new Student(name)
  }
}



object Class3{
  def main(args: Array[String]): Unit = {

    val c1 = new Class3("c1")
    val leo = c1.register("leo")
    leo.introduceMyself




  }



}
