package samples.listen2;

import java.util.concurrent.TimeUnit;

/** 消费者 */
public class Customer {

  private CustomerService afterSaleService;

  public Customer(CustomerService afterSaleService) {
    this.afterSaleService = afterSaleService;
  }

  /**
   * 咨询售后服务
   *
   * @param question
   */
  public void consult(final String question) {

    new Thread(
            () ->
                afterSaleService.attendantHandle(
                    (msg) ->
                        System.out.println(
                            String.format("[%s] 客服反馈：%s", Thread.currentThread().getName(), msg)),
                    question))
        .start();

    try {
      TimeUnit.MILLISECONDS.sleep(999);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    busyOther();
  }

  /** 忙于其他事情去了 */
  public void busyOther() {
    System.out.println(String.format("[%s] 客户处理其他事情去了。", Thread.currentThread().getName()));
  }
}
