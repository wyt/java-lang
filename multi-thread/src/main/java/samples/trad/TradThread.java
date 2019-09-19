package samples.trad;

import java.util.concurrent.TimeUnit;

/**
 * 传统线程技术回顾
 *
 * @author WANG YONG TAO
 * @date 2019/09/19
 */
public class TradThread {

  public static void main(String[] args) {
    Thread thd01 =
        new Thread("Thd01") {
          @Override
          public void run() {
            biz(500);
            // 此处可以引用到this,this为Thread对象
            System.out.println("thd01: " + this);
          }
        };
    thd01.start();

    Thread thd02 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                biz(499);
                // 此处可以引用到this,this为samples.trad.TradThread$2,注意是个匿名类
                System.out.println("thd02: " + this.getClass().getName());
              }
            },
            "Thd02");
    thd02.start();

    Thread thd03 =
        new Thread(
            () -> {
              biz(501);
              // 注意此处无法引用this
              // System.out.println("thd03: " + this);
            },
            "Thd03");
    thd03.start();
  }

  public static void biz(long tm) {
    try {
      TimeUnit.MILLISECONDS.sleep(tm);
      System.out.println(
          String.format(
              "%s: ThreadID=%s,ThreadName=%s,ThreadState=%s",
              Thread.currentThread().getName(),
              Thread.currentThread().getId(),
              Thread.currentThread().getName(),
              Thread.currentThread().getState()));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
