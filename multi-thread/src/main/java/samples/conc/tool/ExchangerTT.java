package samples.conc.tool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger用于进行线程间的数据交换。它提供一个同步点，
 *
 * <p>在这个同步点两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据，
 *
 * <p>如果第一个线程先执行exchange方法，它会一直等待第二个线程也执行exchange，
 *
 * <p>当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 *
 * <p>示例：银行AB岗两人数据校对
 *
 * @author WANG YONG TAO
 * @date 2019/09/23
 */
public class ExchangerTT {

  private static final Exchanger<String> exgr = new Exchanger<String>();
  private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

  public static void main(String[] args) {

    threadPool.execute(
        () -> {
          try {
            // A录入银行流水数据
            String A = "4123.02";
            exgr.exchange(A);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });

    threadPool.execute(
        () -> {
          try {
            // B录入银行流水数据
            String B = "4123.34";
            String A = exgr.exchange(B);

            System.out.println(String.format("A和B数据录入%s一致", A.equals(B) ? "" : "不"));
            System.out.println("A录入的是：" + A + ", B录入是：" + B);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });

    threadPool.shutdown();
  }
}
