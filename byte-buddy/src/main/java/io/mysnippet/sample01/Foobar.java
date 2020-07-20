package io.mysnippet.sample01;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author wangyongtao
 * @date 2020/7/18
 */
public class Foobar {

  public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    // demo01();
    demo02();
  }

  /**
   * 模拟AOP
   *
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static void demo02() throws IllegalAccessException, InstantiationException {

    Service service =
        new ByteBuddy()
            .subclass(Service.class) // 动态生成Service类的子类
            .method(ElementMatchers.any()) // 拦截所有方法
            .intercept(
                Advice.to(LoggerAdvisor.class)) // 使用LoggerAdvisor类作为拦截器，Advice是AOP的概念，似乎一般翻译为「通知」？
            .make() // 作出
            .load(Service.class.getClassLoader()) // 硬塞给ClassLoader
            .getLoaded() // 拿到Class对象
            .newInstance();

    service.bar(123);
    service.foo(456);
  }

  /**
   * byte-buddy上手示例
   *
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static void demo01() throws IllegalAccessException, InstantiationException {

    Class<?> dynamicType =
        new ByteBuddy()
            .subclass(Object.class)
            .name("com.yiche.MyAgent")
            .method(ElementMatchers.named("toString")) // 匹配已instrumented(为...装仪器)的类中的方法
            .intercept(FixedValue.value("Hello,World!")) // implement(修改)之前匹配的方法
            .make()
            .load(Foobar.class.getClassLoader())
            .getLoaded();

    Object instance = dynamicType.newInstance();
    String toString = instance.toString();
    System.out.println(toString);
    System.out.println(instance.getClass().getCanonicalName());
  }
}
