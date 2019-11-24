package io.mysnippet.java8.methodref;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Java 8 方法引用
 *
 * <p>使用lambda表达式创建匿名方法。然而，有时lambda表达式除了调用现有方法之外什么也不做。在这些情况下，通过名称引用现有的方法通常更清楚。方法引用使您能够做到这一点；对于已经有名称的方法，它们是紧凑、易于读取的lambda表达式。
 *
 * <p>https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 * https://www.runoob.com/java/java8-method-references.html
 *
 * @author wangyongtao
 * @date 2019/11/22
 */
public class FoobarTest {

  public static void main(String[] args) {

    // A1 & A2 & A3 三种方式都是等价的

    /** A1: 传统匿名函数方式 */
    Foobar fb01 =
        Foobar.create(
            new Supplier<Foobar>() {
              @Override
              public Foobar get() {
                return new Foobar();
              }
            });

    /** A2: lambda方式 */
    Foobar fb02 = Foobar.create(() -> new Foobar());

    /** A3: 构造方法引用,语法Class::new */
    Foobar fb03 = Foobar.create(Foobar::new);

    final List<Foobar> fbs = Arrays.asList(fb01, fb02, fb03);

    /** 静态方法引用,语法Class::staticMethod */
    fbs.forEach(foobar -> Foobar.commInfo(foobar));
    fbs.forEach(Foobar::commInfo);

    /** 特定类的任意对象方法引用,语法Class::method */
    fbs.forEach(foobar -> foobar.repair());
    fbs.forEach(Foobar::repair);

    /** 特定对象的方法引用,语法instance::method */
    Foobar specific = Foobar.create(Foobar::new);
    fbs.forEach(foobar -> specific.follow(foobar));
    fbs.forEach(specific::follow);

    /** 示例: 特定对象的方法引用 */
    fbs.forEach(System.out::println);
    fbs.forEach(foobar -> System.out.println(foobar));
    fbs.forEach(
        new Consumer<Foobar>() {
          @Override
          public void accept(Foobar foobar) {
            System.out.println(foobar);
          }
        });
  }
}
