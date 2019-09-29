###多线程技术

* `samples.trad` 传统线程包 
  * `TradThread` 传统线程
  * `TradTimer` 传统定时器
  * `TradThreadSync` 传统线程同步
  * `TradThreadSyncComu` 传统线程同步通信
  * `ThreadScopeShare` 线程范围内共享变量示例
* `samples.conc` 并发包
  * `LockSync` 线程锁 Lock的使用
  * `LockSyncComu` Lock & Condition线程同步通信技术
  * `BlockingQueueComu` 用两个只有1个空间的阻塞队列实现同步通信
  * `ThreeConditionComu` 三个Condition的示例
  * `ReadWriteLockSync` 读写锁探讨
  * `CallableAndFuture` 得到线程返回的结果
  * `CompletionServiceTT` CompletionService完成服务实现批量任务
  * `ThreadPoolExecutorTT` ThreadPoolExecutor创建线程池
  * `ScheduledThreadPoolExecutorTT` 定时任务线程池ScheduledThreadPoolExecutor
* `samples.conc.app` 应用示例
  * `CachedData` 缓存多线程示例,应用锁降级提高并发性