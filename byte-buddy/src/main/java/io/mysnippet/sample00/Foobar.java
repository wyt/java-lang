package io.mysnippet.sample00;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author wangyongtao
 * @date 2020/7/20
 */
public class Foobar {

  public static void main(String[] args) throws IllegalAccessException, InstantiationException {

    Service service =
        new ByteBuddy()
            .subclass(Service.class) // 动态生成Service类的子类
            .method(ElementMatchers.isAnnotatedWith(Duration.class)) // 匹配@Duration注解标记的方法
            .intercept(MethodDelegation.to(TimeInterceptor.class)) // 委托
            .make()
            .load(Service.class.getClassLoader())
            .getLoaded()
            .newInstance();

    service.bar(123);
    service.foo(456);
  }
}
