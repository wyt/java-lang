package samples.trad;

import java.util.concurrent.TimeUnit;

/**
 * 传统线程互斥技术
 *
 * <p>1. 非静态同步方法用的都是同一把锁——实例对象本身;
 *
 * <p>2. 静态同步方法用的也是同一把锁——类对象本身;
 *
 * <p>3. 同步代码块可以用实例对象本身(this);也可以用类对象本身T.class;
 *
 * <p>4. 只有用同一把锁的方法或者代码块之间,才会相互构成锁竞争条件.
 *
 * @author WANG YONG TAO
 */
public class TradThreadSync {

  public static void main(String[] args) {
    new TradThreadSync().init();
  }

  private void init() {

    final Outputer outputer = new Outputer();

    new Thread(
            () -> {
              for (int i = 0; i < 3; i++) {
                TradThreadSync.sleep(2000);
                outputer.output2("How are you?");
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

    public void output(String name) {
      int len = name.length();
      // 当前实例对象
      synchronized (this) {
        for (int i = 0; i < len; i++) {
          System.out.print(name.charAt(i));
        }
        System.out.println();
      }
    }

    // 和output方法同一个锁监视器
    public synchronized void output2(String name) {
      int len = name.length();
      for (int i = 0; i < len; i++) {
        System.out.print(name.charAt(i));
      }
      System.out.println();
    }

    // output3使用Outputer.class,作为锁监视器.
    public static synchronized void output3(String name) {
      int len = name.length();
      for (int i = 0; i < len; i++) {
        System.out.print(name.charAt(i));
      }
      System.out.println();
    }
  }
}
