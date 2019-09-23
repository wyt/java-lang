package samples.conc.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 允许一个或多个线程等待其他线程完成操作,类似于join
 *
 * <p>A synchronization aid that allows one or more threads to wait until a set of operations being
 * performed in other threads completes.
 *
 * <p>一种同步辅助，允许一个或多个线程等待直到另外线程中的一组操作完成
 *
 * @author WANG YONG TAO
 * @date 2019/09/23
 */
public class CountdownLatchTT {

  //  等待三个模块全部更新完成,current count 3, updating...
  //  pool-1-thread-2模块更新完成,current count 2
  //  pool-1-thread-3模块更新完成,current count 1
  //  pool-1-thread-1模块更新完成,current count 0
  //  模块全部更新完成准备重启, rebooting...

  public static void main(String[] args) throws InterruptedException {

    ExecutorService threadPool = Executors.newFixedThreadPool(3);

    // 重启锁，当三个模块都升级完成时重启
    final CountDownLatch rebootLatch = new CountDownLatch(3);

    for (int i = 0; i < 3; i++) {
      threadPool.execute(
          () -> {
            try {
              Thread.sleep((long) (Math.random() * 10000));

              System.out.println(
                  String.format(
                      "%s模块更新完成,current count %d",
                      Thread.currentThread().getName(), rebootLatch.getCount() - 1));
              rebootLatch.countDown();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
    }

    System.out.println(
        String.format("等待三个模块全部更新完成,current count %d, updating...", rebootLatch.getCount()));
    rebootLatch.await();

    System.out.println(String.format("模块全部更新完成准备重启, rebooting...", rebootLatch.getCount()));
    threadPool.shutdown();
  }
}
