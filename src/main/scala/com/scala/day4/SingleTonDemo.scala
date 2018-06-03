package cn.edu360.day04

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

// 私有化构造器
class SingleTonDemo private {

}

object SingleTonDemo {
  // 初始化一个实例
  var sing: SingleTonDemo = null

  // 定义一个apply方法，返回类的实例
  def apply(): SingleTonDemo = {
    if (sing == null) {
      sing = new SingleTonDemo()
    }
    // 直接返回结果
    sing
  }
}

object TestSingle {
  def main(args: Array[String]): Unit = {
    //     val d1 = new SingleTonDemo()

    // 地址是否是一样的
    println(SingleTonDemo())
    println(SingleTonDemo())
    println(SingleTonDemo())
  }
}
