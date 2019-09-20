package samples.trad;

/**
 * 传统线程同步通信技术
 *
 * <p>子线程打印10次，接着主线程打印100次...循环如此50次。
 *
 * <p>wait() notify()
 */
public class TradThreadSyncComu {

  public static void main(String[] args) throws InterruptedException {

    final Biz biz = new Biz();

    Runnable subr =
        () -> {
          for (int i = 0; i < 50; i++) {
            try {
              biz.sub(i);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };

    /* 子线程 */
    new Thread(subr).start();

    /* 主线程 */
    for (int i = 0; i < 50; i++) {
      biz.main(i);
    }
  }

  static class Biz {

    boolean onoff = true;

    public synchronized void sub(int i) throws InterruptedException {

      while /*if*/ (!onoff) { // 应该使用while，而不应该使用if，
        this.wait(); // 执行到该行导致线程等待时，线程有可能被cpu虚假唤醒；如果唤醒，上面代码为if，会导致执行继续后续的代码；如果为while，则会继续循环判断条件。
      }

      for (int j = 0; j < 10; j++) {
        System.out.println(String.format("sub thread sequence of %d ,loop of %d", j, i));
      }

      onoff = false; // 退出该方法后，如果子线程继续执行，则让进入while等待。
      this.notify(); // 通知主线程执行
    }

    public synchronized void main(int i) throws InterruptedException {

      while /*if*/ (onoff) { // 应该使用while，而不应该使用if，
        this.wait(); // 执行到该行导致线程等待时，线程有可能被cpu虚假唤醒；如果唤醒，上面代码为if，会导致执行继续后续的代码；如果为while，则会继续循环判断条件。
      }

      for (int j = 0; j < 100; j++) {
        System.out.println(String.format("main thread sequence of %d ,loop of %d", j, i));
      }

      onoff = true; // 退出该方法后，如果主线程继续执行，则进入while等待
      this.notify(); // 通知子线程执行
    }
  }
}
