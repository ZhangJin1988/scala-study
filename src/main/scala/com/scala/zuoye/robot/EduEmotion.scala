
package com.ch.scala.archive.scala28.day04.robot

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

/**
  *
  * Created by ThinkPad 
  * Server
  */
class EduEmotion  extends Actor{
  override def receive: Receive = {
    case "start" => println("服务端已准备就绪")

    case ClientMsgAll(id,msg)=>{
      println(s"接收来自客户端${id} 的消息，msg:${msg}")
      msg match {
        case x:String if(x.contains("叫啥")) => sender() ! EduReMsg("铁扇公举")
        case "是男是女" => sender() ! EduReMsg("老娘是男的")
        case "约么" => sender() ! EduReMsg("叔叔，我们不约")
        case _ => sender() ! EduReMsg("what are you say???")
      }
    }

    case _ => sender() ! EduReMsg("真不知道你在说些什么")
  }
}

object EduEmotion{
  val acs = "emotiion-acs"
  val ac = "emotion-ac"
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = "8888"
    val str = s"""
                 |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
                 |akka.remote.netty.tcp.hostname = ${host}
                 |akka.remote.netty.tcp.port = ${port}
                 |
    """.stripMargin

    val conf = ConfigFactory.parseString(str)
    val acs1 = ActorSystem.create(acs,conf)
    val proxy = acs1.actorOf(Props[EduEmotion],ac)
    proxy ! "start"
  }
}

case class EduReMsg(msg:String)