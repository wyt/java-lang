package samples.conc;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CompletionService批量任务.
 *
 * @author WANG YONG TAO
 * @date 2019/09/25
 */
public class CompletionServiceTT {

  private static final int TASK_NUM = 10;

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);

    for (int i = 1; i <= TASK_NUM; i++) {
      final int seq = i;
      completionService.submit(
          () -> {
            try {
              int sleepTime = new Random().nextInt(5000);
              System.out.println("sleepTime: " + sleepTime + " ");
              Thread.sleep(sleepTime);
              if (seq == 5) {
                // 抛出异常的情况
                throw new RuntimeException("模拟异常");
              }
            } catch (Throwable t) {
              // 必须尽可能的捕获所有异常,发生异常也要返回结果.
              t.printStackTrace();
            }
            return seq;
          });
    }

    // 测试take & poll两种不同方式
    String flag = "take"; // String flag = "poll";

    if ("take".equals(flag)) {
      for (int i = 0; i < 10; i++) {
        // take阻塞
        System.out.println(completionService.take().get());
      }
    } else if ("poll".equals(flag)) {
      int count = 0;
      for (; ; ) {
        // poll非阻塞
        Optional<Future<Integer>> futureOptional = Optional.ofNullable(completionService.poll());
        if (futureOptional.isPresent()) {
          ++count;
          System.out.println(futureOptional.get().get());
        } else {
          TimeUnit.MILLISECONDS.sleep(500);
        }
        // System.out.println("count: " + count + "," + (count == TASK_NUM));
        if (count == TASK_NUM) {
          break;
        }
      }
    }
    threadPool.shutdown();
  }
}
