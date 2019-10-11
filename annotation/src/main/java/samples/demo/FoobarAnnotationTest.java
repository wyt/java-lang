package samples.demo;

import java.lang.reflect.Method;

public class FoobarAnnotationTest {

  public static void main(String[] args) throws SecurityException, IllegalArgumentException {

    Method[] methods = FoobarAnnotationTest.class.getMethods();

    for (Method method : methods) {
      if (method.isAnnotationPresent(FoobarAnnotation.class)) { // 判断该方法是否被类型为MyAnnotation的注解修饰
        // 得到方法上的注解
        FoobarAnnotation myAnnotation = method.getAnnotation(FoobarAnnotation.class);
        // 得到注解属性值
        String foo = myAnnotation.foo();
        String bar = myAnnotation.bar();
        System.out.println(String.format("method=%s,foo=%s,bar=%s", method.getName(), foo, bar));
      }
    }
  }

  @FoobarAnnotation(foo = "foofoo", bar = "barbar")
  public void output() {
    // nothing to do...
  }

  // @FoobarAnnotation /*不可以修饰字段*/
  private String field = "";
}
