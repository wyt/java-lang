package samples.conc;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WANG YONG TAO
 * @date 2019/09/19
 */
class ReadWriteLockSync {

  public static void main(String[] args) {

    final ReadWriteLockSync dict = new ReadWriteLockSync();

    // 读线程不断地读取
    Runnable rnnr =
        () -> {
          for (; ; ) {
            try {
              Set<String> keys = dict.m.keySet();
              for (String key : keys) {
                Data val = dict.get(key);
                System.out.println(
                    String.format(
                        "%s read data: key=%s,val=%s", Thread.currentThread().getName(), key, val));
              }
              TimeUnit.MILLISECONDS.sleep(599);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };

    // 写线程不断写入
    Runnable runw =
        () -> {
          for (; ; ) {
            try {
              String key = UUID.randomUUID().toString();
              Data val = new Data(key);
              dict.put(key, val);
              System.out.println(
                  String.format(
                      "%s put data: key=%s,val=%s", Thread.currentThread().getName(), key, val));
              TimeUnit.MILLISECONDS.sleep(699);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };

    // 开启三个读线程
    for (int i = 0; i < 6; i++) {
      new Thread(rnnr, "THD-R-" + i).start();
    }

    // 开启两个写线程
    for (int i = 0; i < 6; i++) {
      new Thread(runw, "THD-W-" + i).start();
    }
  }

  // -------------------------------

  private final Map<String, Data> m = new TreeMap<>();
  private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  // 一个线程获取了rwl上的写锁，其他线程是不能同时获取rwl上的写锁和读锁的；一个线程获取了rwl上的读锁，其他线程可以再获得读锁，而不能获得写锁。
  private final Lock r = rwl.readLock(); // 共享锁定
  private final Lock w = rwl.writeLock(); // 互斥锁定

  private final Outputer outputer = new Outputer();

  public Data get(String key) {
    r.lock();
    try {
      // 有可能乱序
      // outputer.output("1 2 3 4 5 6 7 8 9");
      return m.get(key);
    } finally {
      r.unlock();
    }
  }

  public Data put(String key, Data value) {
    w.lock();
    try {
      // 保证顺序
      // outputer.output("9 8 7 6 5 4 3 2 1");
      return m.put(key, value);
    } finally {
      w.unlock();
    }
  }

  public static class Data {
    private String id;

    public Data(String id) {
      this.id = id;
    }
  }

  static class Outputer {
    public void output(String str) {
      int len = str.length();
      // 当前实例对象
      for (int i = 0; i < len; i++) {
        System.out.print(str.charAt(i));
      }
      System.out.println();
    }
  }
}
