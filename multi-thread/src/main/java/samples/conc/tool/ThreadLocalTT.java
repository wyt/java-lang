package samples.conc.tool;

import java.util.Random;

/**
 * ThreadLocal类及应用技巧.
 *
 * @author WANG YONG TAO
 * @date 2019/09/25
 */
public class ThreadLocalTT {

  private static ThreadLocal<Integer> X = new ThreadLocal<>();

  public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
      new Thread(
              () -> {
                int data = Math.abs(new Random().nextInt());

                X.set(data);
                // 从ThreadLocal中获取D实例。
                D.getInstance().setSeq(data);

                System.out.println(
                    String.format(
                        "%s has put X & D data %d", Thread.currentThread().getName(), data));

                new A().get();
                new B().get();
              })
          .start();
    }
  }

  static class A {

    public void get() {

      int data = X.get();
      System.out.println(
          String.format("A from %s get X data %d", Thread.currentThread().getName(), data));

      D d = D.getInstance();
      System.out.println(
          String.format(
              "A from %s get D data: seq=%d", Thread.currentThread().getName(), d.getSeq()));
    }
  }

  static class B {

    public void get() {

      int data = X.get();

      System.out.println(
          String.format("B from %s get X data %d", Thread.currentThread().getName(), data));

      D d = D.getInstance();
      System.out.println(
          String.format(
              "B from %s get D data: seq=%d", Thread.currentThread().getName(), d.getSeq()));
    }
  }

  static class D {

    private static ThreadLocal<D> threadLocal = new ThreadLocal<>();

    private int seq;

    private D() {}

    public static D getInstance() {
      D instance = threadLocal.get();
      if (instance == null) {
        instance = new D();
        threadLocal.set(instance);
      }
      return instance;
    }

    public int getSeq() {
      return seq;
    }

    public void setSeq(int seq) {
      this.seq = seq;
    }
  }
}
