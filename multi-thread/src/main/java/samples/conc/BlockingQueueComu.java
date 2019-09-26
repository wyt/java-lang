package samples.conc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列通信
 *
 * <p>用两个只有1个空间的阻塞队列实现同步通信
 *
 * @author WANG YONG TAO
 * @date 2019/09/26
 */
public class BlockingQueueComu {

  public static void main(String[] args) throws InterruptedException {

    final Biz biz = new Biz();
    new Thread(
            () -> {
              for (int i = 1; i <= 5; i++) {
                try {
                  biz.sub(i);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
    for (int i = 1; i <= 5; i++) {
      biz.main(i);
    }
  }

  static class Biz {

    BlockingQueue<Integer> Q1 = new ArrayBlockingQueue<>(1);
    BlockingQueue<Integer> Q2 = new ArrayBlockingQueue<>(1);

    {
      try {
        System.out.println("// ---------------------");
        Q1.put(1); // q1队列上来就满了,不能再放了,再放的话会阻塞当前线程
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public void sub(int i) throws InterruptedException {
      Q1.put(1);
      for (int j = 1; j <= 10; j++) {
        System.out.println("sub thread sequece of " + j + ",loop of " + i);
      }
      Q2.take();
    }

    public void main(int i) throws InterruptedException {
      Q2.put(1);
      for (int j = 1; j <= 10; j++) {
        System.out.println("main thread sequece of " + j + ",loop of " + i);
      }
      Q1.take(); // 取出一个值后，又可以放了
    }
  }
}
