package samples.trad;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 传统定时器技术回顾
 *
 * @author WANG YONG TAO
 * @date 2019/09/19
 */
public class TradTimer {

  private static int count = 0;

  public static void main(String[] args) {
    // 用于计时的线程
    new Thread(() -> secondhand()).start();

    TradTimer timer = new TradTimer();
    timer.testTimer01();
    timer.testTimer02();
    timer.testTimer03();
  }

  public void testTimer01() {
    /** 面向对象的方式思考：一个定时器实例，拥有计划schedule方法，具体的任务由TimerTask实现. */
    new Timer("mytimer-0")
        .schedule(
            new TimerTask() {
              @Override
              public void run() {
                System.out.println(Thread.currentThread().getName() + " 000bombing000 ");
              }
            },
            5000,
            3000);
  }

  public void testTimer02() {
    /** 面向对象的方式思考：一个定时器实例，拥有计划schedule方法，具体的任务有TimerTask实现 */
    new Timer("mytimer-1")
        .schedule(
            new TimerTask() {
              @Override
              public void run() {
                System.out.println(Thread.currentThread().getName() + " 111bombing111 ");
              }
            },
            3000,
            4000);
  }

  /** 2-4-2...效果 */
  public void testTimer03() {

    class MyTimerTask extends TimerTask {
      @Override
      public void run() {
        // count 1,0来回变化
        count = (count + 1) % 2;
        System.out.println(Thread.currentThread().getName() + " 222bombing222 ");
        new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count);
      }
    }

    new Timer().schedule(new MyTimerTask(), 2000);
  }

  /** 秒针计时 */
  public static void secondhand() {
    for (; ; ) {
      System.out.println(Calendar.getInstance().get(Calendar.SECOND));
      try {
        TimeUnit.MILLISECONDS.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
