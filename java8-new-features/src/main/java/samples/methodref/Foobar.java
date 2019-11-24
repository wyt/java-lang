package samples.methodref;

import java.util.function.Supplier;

/**
 * @author wangyongtao
 * @date 2019/11/22
 */
class Foobar {

  /**
   * 创建一个Foobar对象
   *
   * @param supplier
   * @return
   */
  public static Foobar create(final Supplier<Foobar> supplier) {
    return supplier.get();
  }

  public static void commInfo(final Foobar foobar) {
    System.out.println("Collided " + foobar.toString());
  }

  public void follow(final Foobar foobar) {
    System.out.println("Following the " + foobar.toString());
  }

  public void repair() {
    System.out.println("Repaired " + this.toString());
  }
}
