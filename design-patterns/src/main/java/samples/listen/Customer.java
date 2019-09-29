package samples.listen;

/**
 * 客户
 *
 * @author WANG YONG TAO
 * @date 2019/09/27
 */
public class Customer implements FinishedListener {

  private CustomerService customerService;

  public Customer(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * 咨询售后服务
   *
   * @param question
   */
  public void consult(final String question) {

    customerService.setFinishedListener(this);
    customerService.attendantHandle(question);

    // new Thread(() -> afterSaleService.attendantHandle(this, question)).start();

    busyOther();
  }

  /** 忙于其他事情去了 */
  public void busyOther() {
    System.out.println(String.format("[%s] 客户处理其他事情去了。", Thread.currentThread().getName()));
  }

  @Override
  public void onFinished(String msg) {
    System.out.println(String.format("[%s] 客服反馈：%s", Thread.currentThread().getName(), msg));
  }
}
