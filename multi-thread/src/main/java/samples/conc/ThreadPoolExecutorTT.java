package samples.conc;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor,线程池不允许通过Executors创建.
 *
 * @author WANG YONG TAO
 * @date 2019/09/26
 */
public class ThreadPoolExecutorTT {

  /** 手动创建一个线程池,自定义参数. */
  static final ExecutorService THDPOOL =
      new ThreadPoolExecutor(
          3, // 核心线程数
          10, // 最大线程数
          60 * 1000L, // 核心线程以外的空闲线程存活时间
          TimeUnit.MILLISECONDS, // 存活时间单位
          new LinkedBlockingQueue<>(), // 任务队列
          Executors.defaultThreadFactory(), // 线程工厂
          new ThreadPoolExecutor
              .CallerRunsPolicy()); // 定义当线程不够用或者工作队列满的情况下的处理器，CallerRunsPolicy策略貌似会在execute方法的调用线程中运行被拒绝的任务

  public static void main(String[] args) {

    for (int i = 1; i <= 10; i++) {
      final int task = i;
      // 提交任务,ExecutorService分配线程执行.
      THDPOOL.execute(
          () -> {
            for (int j = 1; j <= 10; j++) {
              try {
                TimeUnit.MILLISECONDS.sleep(99);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(
                  String.format(
                      "%s is looping of %d for task of %d",
                      Thread.currentThread().getName(), j, task));
            }
          });
    }

    // 用于测试上面循环已经完成
    System.out.println("all of 10 tasks have committed! ");
    // 任务执行完了之后，关闭线程池，退出虚拟机
    THDPOOL.shutdown();
  }
}
