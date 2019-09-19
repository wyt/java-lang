package samples.trad;

/**
 * 传统线程同步通信技术
 *
 * <p>子线程打印10次，接着主线程打印100次...循环如此50次。
 *
 * <p>wait() notify()
 */
public class TradThreadComu {

  public static void main(String[] args) {

    /* Runnable内部类中引用了business变量，在jdk1.6下必须显示声明为final；jdk1.8下虽然不用显示声明为final，但是在内部类中不允许对business修改 */
    final Business business = new Business();

    /* 子线程 */
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 1; i <= 50; i++) {
                  business.sub(i);
                }
              }
            })
        .start();

    /* 主线程 */
    for (int i = 1; i <= 50; i++) {
      business.main(i);
    }
  }
}

class Business {

  /* 标记 */
  private boolean bShouldSub = true;

  public synchronized void sub(int i) {
    while (!bShouldSub) // 此处用的是while而不是if，为了防止线程虚假唤醒
    {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int j = 1; j <= 10; j++) {
      System.out.println("sub thread sequence of " + j + " ,loop of " + i);
    }

    bShouldSub = false; // 退出该方法后，如果子线程继续执行，则让进入while等待。
    this.notify(); // 通知主线程执行
  }

  public synchronized void main(int i) {
    while (bShouldSub) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    for (int j = 1; j <= 100; j++) {
      System.out.println("main thread sequence of " + j + " ,loop of " + i);
    }

    bShouldSub = true; // 退出该方法后，如果主线程继续执行，则进入while等待
    this.notify(); // 通知子线程执行
  }
}
