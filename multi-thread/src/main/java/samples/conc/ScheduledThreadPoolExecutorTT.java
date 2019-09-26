package samples.conc;

import java.util.concurrent.*;

/**
 * 定时任务线程池.
 *
 * @author WANG YONG TAO
 * @date 2019/09/26
 */
public class ScheduledThreadPoolExecutorTT {

  private static final ScheduledExecutorService SES =
      new ScheduledThreadPoolExecutor(
          3, Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

  public static void main(String[] args) {
    SES.scheduleAtFixedRate(
        () -> System.out.println(String.format("%s bombing!", Thread.currentThread().getName())),
        6,
        2,
        TimeUnit.SECONDS);
  }
}
