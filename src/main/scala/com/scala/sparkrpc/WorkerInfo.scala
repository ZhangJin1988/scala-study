package sparkrpc

/**
  * Created by Huge
  * DATE: 2018/5/30
  * Desc: 
  */

// 封装 worker的信息
class WorkerInfo(workerId: String, memory: Int, cores: Int) {

  // 最后一次的心跳时间 成员属性
  var lastHeartTime: Long = _
}
