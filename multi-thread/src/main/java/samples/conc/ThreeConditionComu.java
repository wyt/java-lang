package samples.conc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个condition的情况
 *
 * @author WANG YONG TAO
 * @data 2019/09/24
 */
public class ThreeConditionComu {

  public static void main(String[] args) {

    final Biz business = new Biz();

    new Thread(
            () -> {
              for (int i = 1; i <= 3; i++) {
                business.sub2(i);
              }
            })
        .start();

    new Thread(
            () -> {
              for (int i = 1; i <= 3; i++) {
                business.sub3(i);
              }
            })
        .start();

    for (int i = 1; i <= 3; i++) {
      business.sub1(i);
    }
  }

  static class Biz {

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int shouldSub = 1;

    public void sub2(int i) {
      lock.lock();
      try {
        while (shouldSub != 2) {
          try {
            condition2.await();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        for (int j = 1; j <= 5; j++) {
          System.out.println("sub2 thread sequence of " + j + ",loop of " + i);
        }
        shouldSub = 3;
        condition3.signal();
      } finally {
        lock.unlock();
      }
    }

    public void sub3(int i) {
      lock.lock();
      try {
        while (shouldSub != 3) {
          try {
            condition3.await();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        for (int j = 1; j <= 6; j++) {
          System.out.println("sub3 thread sequence of " + j + ",loop of " + i);
        }
        shouldSub = 1;
        condition1.signal();
      } finally {
        lock.unlock();
      }
    }

    public void sub1(int i) {
      lock.lock();
      try {
        while (shouldSub != 1) {
          try {
            condition1.await();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        for (int j = 1; j <= 4; j++) {
          System.out.println("sub1 thread sequence of " + j + ",loop of " + i);
        }
        shouldSub = 2;
        condition2.signal();
      } finally {
        lock.unlock();
      }
    }
  }
}
