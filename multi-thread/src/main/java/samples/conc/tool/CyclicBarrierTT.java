package samples.conc.tool;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 *
 * <p>直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 *
 * @author WANG YONG TAO
 * @date 2019/09/23
 */
public class CyclicBarrierTT {

  //  线程pool-1-thread-3到达「集合点1」，当前已有1个已经到达，正在等候
  //  线程pool-1-thread-2到达「集合点1」，当前已有2个已经到达，正在等候
  //  线程pool-1-thread-1到达「集合点1」，当前已有3个已经到达，都到齐了，继续走啊
  //  线程pool-1-thread-3到达「集合点2」，当前已有1个已经到达，正在等候
  //  线程pool-1-thread-1到达「集合点2」，当前已有2个已经到达，正在等候
  //  线程pool-1-thread-2到达「集合点2」，当前已有3个已经到达，都到齐了，继续走啊
  //  线程pool-1-thread-3到达「集合点3」，当前已有1个已经到达，正在等候
  //  线程pool-1-thread-2到达「集合点3」，当前已有2个已经到达，正在等候
  //  线程pool-1-thread-1到达「集合点3」，当前已有3个已经到达，都到齐了，继续走啊

  public static void main(String[] args) {

    ExecutorService service = Executors.newCachedThreadPool();

    final CyclicBarrier cb = new CyclicBarrier(3);

    for (int i = 0; i < 3; i++) {
      Runnable runnable =
          () -> {
            try {
              Thread.sleep((long) (Math.random() * 10000));
              System.out.println(
                  String.format(
                      "线程%s到达「集合点1」，当前已有%d个已经到达，%s",
                      Thread.currentThread().getName(),
                      (cb.getNumberWaiting() + 1),
                      (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候")));
              cb.await();

              Thread.sleep((long) (Math.random() * 10000));
              System.out.println(
                  String.format(
                      "线程%s到达「集合点2」，当前已有%d个已经到达，%s",
                      Thread.currentThread().getName(),
                      (cb.getNumberWaiting() + 1),
                      (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候")));
              cb.await();

              Thread.sleep((long) (Math.random() * 10000));
              System.out.println(
                  String.format(
                      "线程%s到达「集合点3」，当前已有%d个已经到达，%s",
                      Thread.currentThread().getName(),
                      (cb.getNumberWaiting() + 1),
                      (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候")));
              cb.await();
            } catch (Exception e) {
              e.printStackTrace();
            }
          };
      service.execute(runnable);
    }
    service.shutdown();
  }
}
