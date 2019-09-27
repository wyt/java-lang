package samples.callback;

import java.util.concurrent.TimeUnit;

/** 售后服务中心 */
public class AfterSaleService {

  /**
   * 坐席处理
   *
   * @param callBack
   * @param question
   * @throws InterruptedException
   */
  public void attendantHandle(CallBack callBack, String question) {
    System.out.println(String.format("[%s] 消费者咨询: %s", Thread.currentThread().getName(), question));
    try {
      TimeUnit.MILLISECONDS.sleep(2999);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String solution = "尊敬的客户您好，请您重启设备试下，感谢您的反馈！";
    callBack.solve(solution);
  }
}
