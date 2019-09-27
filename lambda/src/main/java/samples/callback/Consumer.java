package samples.callback;

import java.util.concurrent.TimeUnit;

/** 消费者 */
public class Consumer implements CallBack {

  private AfterSaleService afterSaleService;

  public Consumer(AfterSaleService afterSaleService) {
    this.afterSaleService = afterSaleService;
  }

  /**
   * 咨询售后服务
   *
   * @param question
   */
  public void consult(final String question) {

    new Thread(() -> afterSaleService.attendantHandle(this, question)).start();

    try {
      TimeUnit.MILLISECONDS.sleep(999);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    busyOther();
  }

  /** 忙于其他事情去了 */
  public void busyOther() {
    System.out.println(String.format("[%s] 消费者处理其他事情去了。", Thread.currentThread().getName()));
  }

  @Override
  public void solve(String result) {
    System.out.println(String.format("[%s] 售后反馈：%s", Thread.currentThread().getName(), result));
  }
}
