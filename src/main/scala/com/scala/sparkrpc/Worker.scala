package sparkrpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

// 主构造器 传递参数
class Worker(masterHost: String, masterPort: Int, var memory: Int, var cores: Int) extends Actor {

  // 随机获取一个workerId
  private val workerId = UUID.randomUUID().toString
  var masterProxy: ActorSelection = null

  // 获取到master的代理
  override def preStart(): Unit = {

    val path = s"akka.tcp://${Master.MASTER_ACTOR_SYSTEM_NAME}@${masterHost}:${masterPort}/user/${Master.MASTER_ACTOR_NAME}"
    masterProxy = context.actorSelection(path)

    // 发送注册请求消息  2  To   4  For
    // 发送一个样例类 封装了3个数据
    masterProxy ! Register2Master(workerId, memory, cores)
  }

  override def receive: Receive = {

    // Worker 接收注册成功的消息
    case WorkerRegisteredSuccess => {
      println(s" worker Register success ，workerid=${workerId}")

      /**
        * initialDelay: FiniteDuration,   定时任务启动的延迟时间    不延迟  0
        * interval:     FiniteDuration,    间隔时间   每隔多长时间发送 一次心跳信息
        * receiver:     ActorRef,           定时任务 消息的接收者   Master
        * message:      Any                  发送的消息  心跳   case class  workerId
        */
      // 启动定时任务

      // 导入一个ExecutionContext
      import context.dispatcher
      // 导入时间单位
      import scala.concurrent.duration._
      context.system.scheduler.schedule(Constants.INITIALDELAY seconds, Constants.WORKER_INTERVAL seconds, sender(), SendHeartBeat2Master(workerId))

      // 更复杂的写法，self 先把消息发送给自己，然后自己去接收  ，接收之后，再发给master

    }

    case _ => println("no match")
  }
}

object Worker {
  val WORKER_ACTOR_SYSTEM_NAME = "worker-systemName"
  val WORKER_ACTOR_NAME = "worker"

  def main(args: Array[String]): Unit = {

    if (args.length != 6) {
      println("Usage : sparkrpc.Worker workerHost, workerPort,masterHost,masterPort,memory,cores")
      sys.exit(1)
    }
    val Array(workerHost, workerPort, masterHost, masterPort, memory, cores) = args
    // 配置信息
    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = ${workerHost}
         |akka.remote.netty.tcp.port = ${workerPort}
      """
        .stripMargin

    val config = ConfigFactory.parseString(str)

    val acs = ActorSystem.create(WORKER_ACTOR_SYSTEM_NAME, config)

    //构建 带参数的的Worker
    acs.actorOf(Props(new Worker(masterHost, masterPort.toInt, memory.toInt, cores.toInt)), WORKER_ACTOR_NAME)

  }
}
