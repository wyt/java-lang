package io.mysnippet.sample00;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author wangyongtao
 * @date 2020/7/13
 */
@SuppressWarnings("all")
public class TimeInterceptor {

  @RuntimeType
  //  public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable)
  public static Object intercept(
      @This Object obj,
      @AllArguments Object[] allArguments,
      @SuperCall Callable<?> callable,
      @Origin Method method)
      throws Exception {

    long start = System.currentTimeMillis();

    try {
      // 原有函数执行
      return callable.call();
    } finally {

      System.out.println(obj);
      System.out.println(allArguments);
      System.out.println(method);
      System.out.println(
          "方法: " + method.getName() + ": 耗时 " + (System.currentTimeMillis() - start) + "ms");
    }
  }
}
