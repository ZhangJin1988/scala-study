package sparkrpc

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

object SparkMessage {

}

// 写所有的样例类和样例对象

// worker 向master 注册的类
case class Register2Master(workerId: String, memory: Int, cores: Int)

// master 返回 注册成功的消息
case object WorkerRegisteredSuccess

// worker  发送给master的 定时心跳
case class SendHeartBeat2Master(workerId: String)

// 检测超时的worker
case object CheckWorkersStatus