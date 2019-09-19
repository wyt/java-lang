package io.mysnippet.multithread;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 观察线程所处的状态
 *
 * @author wangyongtao
 * @date 2019/08/22
 */
public class ThreadState {

  public static void main(String[] args) {
    Thread thd01 =
        new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(120);
              } catch (InterruptedException e) {
                // ignore
              }
            },
            "Thread-01");

    Thread thd02 =
        new Thread(
            () -> {
              doSorting();
            },
            "Thread-02");

    Thread thd03 =
        new Thread(
            () -> {
              doSorting();
            },
            "Thread-03");

    thd01.start();
    thd02.start();
    thd03.start();
  }

  public static synchronized void doSorting() {
    for (; ; ) {
      // 生成随机数组
      double[] a = new double[100];
      for (int i = 0; i < a.length; i++) {
        a[i] = new Random().nextDouble();
      }
      // 对随机数组进行排序，使其消耗CPU
      Arrays.sort(a);
    }
  }
}
