package samples.callback2;

import java.util.concurrent.TimeUnit;

/**
 * 客户
 *
 * @author WANG YONG TAO
 * @date 2019/09/27
 */
public class Customer {

  private CustomerService customerService;

  public Customer(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * 业务咨询
   *
   * @param question
   */
  public void consult(final String question) {

    new Thread(
            () ->
                customerService.attendantHandle(
                    (result) ->
                        System.out.println(
                            String.format(
                                "[%s] 客服反馈：%s", Thread.currentThread().getName(), result)),
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
