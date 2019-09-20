package samples.conc;

import samples.trad.TradThreadSync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程锁 Lock的使用
 *
 * @author WANG YONG TAO
 * @date 2019/09/19
 */
public class LockSync {

  public static void main(String[] args) {
    new LockSync().init();
  }

  private void init() {

    final Outputer outputer = new Outputer();

    new Thread(
            () -> {
              for (int i = 0; i < 3; i++) {
                sleep(2000);
                outputer.output("How are you?");
              }
            })
        .start();

    new Thread(
            () -> {
              for (int i = 0; i < 3; i++) {
                TradThreadSync.sleep(2000);
                outputer.output("Fine,thank you.");
              }
            })
        .start();
  }

  public static void sleep(long tm) {
    try {
      TimeUnit.MILLISECONDS.sleep(tm);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static class Outputer {

    // 使用重入锁
    Lock lock = new ReentrantLock();

    public void output(String name) {
      int len = name.length();
      lock.lock();
      try {
        for (int i = 0; i < len; i++) {
          System.out.print(name.charAt(i));
        }
        System.out.println();
      } finally {
        lock.unlock();
      }
    }
  }
}
