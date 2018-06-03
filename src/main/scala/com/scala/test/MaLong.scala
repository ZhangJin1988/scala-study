package com.scala.test

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


class Malong extends Actor {
  override def receive: Receive = {
    case "回个球" => {
      Thread.sleep(2 * 1000)
      println("malong 发球 ")
      sender() ! "发个球"
    }
    case _ => println("no match")
  }

  //         1
  override def preStart(): Unit = {
    println("this is preStart")

    val path = s"akka.tcp://${AlphaGo.ALPHAGO_ACTOR_SYSTEM_NAME}@127.0.0.1:8888/user/${AlphaGo.ALPHAGO_ACTOR_NAME}"
    // alphago
    val goProxy: ActorSelection = context.actorSelection(path)
    // d
    goProxy ! "发个球"
  }
}


object Malong {
  //       ctrl + shift + u
  val MALONG_ACTOR_SYSTEM_NAME = "MALONG-systemName"
  val MALONG_ACTOR_NAME = "MALONG"

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("Usage : pingpong.Malong <host> <port>")
      sys.exit(1)
    }
    val Array(host, port) = args
    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = ${host}
         |akka.remote.netty.tcp.port = ${port}
      """
        .stripMargin
    val config = ConfigFactory.parseString(str)
    val acs = ActorSystem.create(MALONG_ACTOR_SYSTEM_NAME, config)
    acs.actorOf(Props(new Malong()), MALONG_ACTOR_NAME)
  }
}


