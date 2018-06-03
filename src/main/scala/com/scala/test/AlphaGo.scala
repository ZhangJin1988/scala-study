package com.scala.test

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class AlphaGo extends Actor {
  override def receive: Receive = {
    case "发个球" => {
      println("回个球")
      sender() ! "回个球"
    }

    case _ => println("no match")
  }

}


object AlphaGo {
  //       ctrl + shift + u
  val ALPHAGO_ACTOR_SYSTEM_NAME = "AlphaGo-systemName"
  val ALPHAGO_ACTOR_NAME = "AlphaGo"

  def main(args: Array[String]): Unit = {
    //
    if (args.length != 2) {
      println("Usage : pingpong.AlphaGo <host> <port>")
      sys.exit(1)
    }
    //     =
    val Array(host, port) = args
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
