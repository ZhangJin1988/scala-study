package main.scala.com.actor32.test

//import akka.actor.{Actor, ActorRef, ActorSystem, Props}
//import akka.remote.ContainerFormats.ActorRef
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

class HelloActor extends Actor {
  //  override type Receive = Receive

  override def receive: Actor.Receive = {
    case "hello actor" => println("receive success")
    case _ => println("no match ")
  }

}

object HelloActor {

  //定义一个actorSystem的名称
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
      """.stripMargin
    // 把 配置参数 转换为 config 对象
    val config: Config = ConfigFactory.parseString(str)


    // 需要有ActorSystem 创建actorSystem
    val acs: ActorSystem = ActorSystem.create(ACTOR_SYSTEM_NAME, config)

    // 启动actor 并获取到一个代理对象
    val proxy: ActorRef = acs.actorOf(Props(new HelloActor()), ACTOR_NAME)

    // 发送消息
    proxy ! "hello actor"

  }


}


