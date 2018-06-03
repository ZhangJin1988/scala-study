
package com.ch.scala.archive.scala28.day04.robot

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

/**
  *
  * Created by ThinkPad 
  * Server
  */
class Client extends Actor{
  var eduproxy: ActorSelection = null
  override def preStart(): Unit = {
    val path = s"akka.tcp://${EduEmotion.acs}@127.0.0.1:8888/user/${EduEmotion.ac}"
     eduproxy = context.actorSelection(path)
//    eduproxy ! ""
  }

  override def receive: Receive = {
    case "start" => println("神州client已准备就绪，可随时撩妹")
    case ClientMsg(msg) => eduproxy ! ClientMsgAll("c1",msg)
    case EduReMsg(msg) => println(s"接收服务端回复消息，msg=${msg}")
  }
}
object Client{

  val client_acs = "client-acs"
  val client_ac = "client_ac"
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = "9999"
    val str = s"""
                 |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
                 |akka.remote.netty.tcp.hostname = ${host}
                 |akka.remote.netty.tcp.port = ${port}
                 |
    """.stripMargin
    val conf = ConfigFactory.parseString(str)
    val acs = ActorSystem.create(client_acs,conf)
    val proxy = acs.actorOf(Props[Client],client_ac)

    proxy ! "start"
    while (true){
      val line = StdIn.readLine()
      proxy ! ClientMsg(line)
    }
  }
}

case class ClientMsg(msg:String)
case class ClientMsgAll(uuid:String,msg:String)