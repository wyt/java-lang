package samples.callback;

import java.util.concurrent.TimeUnit;

/**
 * 客服中心
 *
 * @author WANG YONG TAO
 * @date 2019/09/27
 */
public class CustomerService {

  /**
   * 坐席处理
   *
   * @param callBack
   * @param question
   * @throws InterruptedException
   */
  public void attendantHandle(CallBack callBack, String question) {
    System.out.println(String.format("[%s] 客户咨询: %s", Thread.currentThread().getName(), question));
    try {
      TimeUnit.MILLISECONDS.sleep(2999);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String solution = "您好！您本月国内通用流量共有7.51GB,截至19年9月27日9时38分，剩余1.50GB。【中国X动】";
    callBack.solve(solution);
  }
}
