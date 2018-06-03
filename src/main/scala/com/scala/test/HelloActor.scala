package com.scala.test

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}


class HelloActor extends Actor {
  //          PartialFunction[Any, Unit]
  override def receive: Receive = {
    case "hello actor"
    => println("receive success")
    case _ => println("no match ")
  }

}

object HelloActor {
  //      actorSystem
  val ACTOR_SYSTEM_NAME = "hello-actor-systemName"
  val ACTOR_NAME = "hello-actor"

  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = 8888
    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = ${host}
         |akka.remote.netty.tcp.port = ${port}
"""
        .stripMargin
    //            config
    val config: Config = ConfigFactory.parseString(str)
    //    ActorSystem   actorSystem
    val acs: ActorSystem = ActorSystem.create(ACTOR_SYSTEM_NAME, config)
    //   actor   e
    val proxy: ActorRef = acs.actorOf(Props(new HelloActor()), ACTOR_NAME)
    // d
    proxy ! "hello actor"
  }

}

