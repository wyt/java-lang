package samples.simple;

/**
 * 自定义注解的使用方式
 *
 * @author 王永涛
 * @since 2012-7-9 上午11:40:19
 */
public class Main {

  //    @FooAnnotation({"foo", "bar"})
  @FooAnnotation(value = {"foo", "bar"})
  public void method() {}

  @BarAnnotation(value = {Grade.A, Grade.B})
  public void method2() {}
}
