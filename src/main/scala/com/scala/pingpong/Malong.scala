package pingpong

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

class Malong extends Actor {


  override def receive: Receive = {
    case  "回个球" => {
      Thread.sleep(2*1000)
      println("malong  发球 ")
      sender() ! "发个球"
    }

    case _ => println("no match")
  }

  // 会执行几次？？ 1次
  override def preStart(): Unit = {
    println("this is preStart")
    // 去获取到AlphaGo的代理对象
    // path 由4部分组成 协议 actorsystem name  地址 host：port  指定连接哪一个actor
    val path = s"akka.tcp://${AlphaGo.ALPHAGO_ACTOR_SYSTEM_NAME}@127.0.0.1:8888/user/${AlphaGo.ALPHAGO_ACTOR_NAME}"
    // alphago的代理对象
    val goProxy: ActorSelection = context.actorSelection(path)
    // 发球
    goProxy ! "发个球"
  }
}

object Malong {
  // 大小写转换 ctrl + shift + u
  val MALONG_ACTOR_SYSTEM_NAME = "MALONG-systemName"
  val MALONG_ACTOR_NAME = "MALONG"

  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      println("Usage : pingpong.Malong <host> <port>")
      sys.exit(1)
    }
    val Array(host, port) = args
    // 配置信息
    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = ${host}
         |akka.remote.netty.tcp.port = ${port}
      """
        .stripMargin

    val config = ConfigFactory.parseString(str)

    val acs = ActorSystem.create(MALONG_ACTOR_SYSTEM_NAME, config)

    //    val getProxy: ActorRef =
    acs.actorOf(Props(new Malong()), MALONG_ACTOR_NAME)
    //    getProxy ! "发个球"

    //    val path = s"akka.tcp://${AlphaGo.ALPHAGO_ACTOR_SYSTEM_NAME}@127.0.0.1:8888/user/${AlphaGo.ALPHAGO_ACTOR_NAME}"
    //    //    // alphago的代理对象
    //        val goProxy: ActorSelection = context.actorSelection(path)
    //        // 发球
    //        goProxy ! "发个球"
    //
    //  }
  }
}