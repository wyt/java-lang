package samples.conc.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量,用于控制并发线程数
 *
 * @author WANG YONG TAO
 * @date 2019/09/23
 */
public class SemaphoreTT {

  // 线程池线程总数
  private static final int THREAD_COUNT = 30;
  // 最高10个并发,只有一个permit时,Semaphore可以当成锁来使用
  private static final int SEMAPHORE_PERMITS = 10;
  // 线程池
  private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
  // 信号量
  private static final Semaphore semaphore = new Semaphore(SEMAPHORE_PERMITS);

  public static void main(String[] args) {

    for (int i = 0; i < THREAD_COUNT; i++) {
      Runnable runnable =
          () -> {
            try {
              // 获取一个许可证
              semaphore.acquire();
              System.out.println(
                  String.format(
                      "线程%s进入，当前并发数为%d，正在等待获取许可证的线程数%d",
                      Thread.currentThread().getName(),
                      SEMAPHORE_PERMITS - semaphore.availablePermits(),
                      semaphore.getQueueLength()));

              Thread.sleep((long) (Math.random() * 10000));
              System.out.println(
                  String.format(
                      "线程%s进入，当前并发数为%d，正在等待获取许可证的线程数%d",
                      Thread.currentThread().getName(),
                      SEMAPHORE_PERMITS - semaphore.availablePermits(),
                      semaphore.getQueueLength()));
            } catch (InterruptedException e) {
              e.printStackTrace();
            } finally {
              // 归还许可证, 注意一定要在finally中执行
              semaphore.release();
            }
          };
      threadPool.execute(runnable);
    }
    threadPool.shutdown();
  }
}
