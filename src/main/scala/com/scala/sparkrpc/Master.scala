package sparkrpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

class Master extends Actor {

  // 次数
  val DEADWORKER_CHECK_TIMES = 2
  // 时间单位
  val MILLES2SECONDS = 1000
  // 存储的是 所有的worker信息
  val mp = new mutable.HashMap[String, WorkerInfo]()

  // 在preStart方法中，启动定时任务，检测超时的worker


  override def preStart(): Unit = {

    // Master 自己的定时任务
    import scala.concurrent.duration._
    import context.dispatcher
    // 心跳时间 10s  检测的时间： 12s
    context.system.scheduler.schedule(Constants.INITIALDELAY seconds, Constants.MASTER_INTERVAL seconds, self, CheckWorkersStatus)
  }

  override def receive: Receive = {

    case CheckWorkersStatus => {

      // 检测超时的worker  9:00 10:00 11:00  12:00
      // 定义规则 ： worker的最后一次心跳时间，  缺少2次心跳，就认为挂掉了
      // 当前时间  - 最后一次心跳时间  > 心跳间隔 * 2

      // 从 mp 中 找出所有的挂掉的worker
      val deadWorkers: mutable.HashMap[String, WorkerInfo] = mp.filter(w => {
        System.currentTimeMillis() - w._2.lastHeartTime > DEADWORKER_CHECK_TIMES * Constants.WORKER_INTERVAL * MILLES2SECONDS
      })

      // 从mp中删除掉挂掉的workers

      // 2 种
      mp --= deadWorkers.keys

      println(s" now alive workers = ${mp.size}")
      // 遍历删除
  /*    deadWorkers.foreach(d =>{
        mp -= d._1
      })
*/
      // 不能在一边遍历集合，一边删除集合
    /* 错误代码演示
     mp.foreach(d =>{
        mp --= deadWorkers.keys
      })*/


    }

    // Master 接收 worker的注册信息
    case Register2Master(workerId, memory, cores) => {

      println("register success")
      // 1， 存储worker的数据    Map

      // 创建一个worker的实例
      val wInfo: WorkerInfo = new WorkerInfo(workerId, memory, cores)
      // 存储到map中
      mp(workerId) = wInfo

      println(s"add  worker success,current lives workers = ${mp.size}")

      // 2 返回注册成功的消息
      sender() ! WorkerRegisteredSuccess
    }

    case SendHeartBeat2Master(workerId) => {

      // Master 接收到worker的定时心跳信息
      // 更新worker的心跳时间  最后一次

      val workInfo: WorkerInfo = mp(workerId)
      // 把当前时间，设置为最后一次心跳的时间
      workInfo.lastHeartTime = System.currentTimeMillis()
      println(s"update worker heart time workerid=${workerId}")
    }
  }
}

object Master {
  // 大小写转换 ctrl + shift + u
  val MASTER_ACTOR_SYSTEM_NAME = "master-systemName"
  val MASTER_ACTOR_NAME = "Master"

  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      println("Usage : sparkrpc.Master <host> <port>")
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

    val acs = ActorSystem.create(MASTER_ACTOR_SYSTEM_NAME, config)

    acs.actorOf(Props(new Master()), MASTER_ACTOR_NAME)

  }
}