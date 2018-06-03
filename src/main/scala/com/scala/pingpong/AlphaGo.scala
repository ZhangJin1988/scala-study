package pingpong

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

class AlphaGo extends Actor {
  override def receive: Receive = {
    // 回复信息
    //   给其他人回复消息，使用 sender()
    //    给自己发送消息 使用 self
    case "发个球" => {
      println("回个球")
      sender() ! "回个球"
    }

    case _ => println("no match")
  }
}

object AlphaGo {

  // 大小写转换 ctrl + shift + u
  val ALPHAGO_ACTOR_SYSTEM_NAME = "AlphaGo-systemName"
  val ALPHAGO_ACTOR_NAME = "AlphaGo"

  def main(args: Array[String]): Unit = {

    // 参数判断
    if (args.length != 2) {
      println("Usage : pingpong.AlphaGo <host> <port>")
      sys.exit(1)
    }
    // 通过参数传递
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

    val acs: ActorSystem = ActorSystem.create(ALPHAGO_ACTOR_SYSTEM_NAME, config)

    val go: ActorRef = acs.actorOf(Props(new AlphaGo()), ALPHAGO_ACTOR_NAME)
  }
}