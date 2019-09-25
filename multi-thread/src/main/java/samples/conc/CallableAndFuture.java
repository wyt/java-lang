package samples.conc;

import java.util.concurrent.*;

/**
 * 得到线程返回的结果, Callable<Void>表示无返回值的任务.
 *
 * @author WANG YONG TAO
 * @date 2019/09/25
 */
public class CallableAndFuture {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    /** 0. 创建一个线程池 */
    ExecutorService threadPool = Executors.newSingleThreadExecutor();

    /** 1. 提交一个Callable线程任务 */
    Callable<String> call =
        () -> {
          System.out.println(
              String.format("%s thread ready computation", Thread.currentThread().getName()));
          Thread.sleep(2000);
          return "hello";
        };
    Future<String> future = threadPool.submit(call);

    System.out.println(
        String.format("%s thread waiting get result", Thread.currentThread().getName()));

    // 等待计算完成
    String result = future.get();
    System.out.print("result: " + result);

    threadPool.shutdown();
  }
}
