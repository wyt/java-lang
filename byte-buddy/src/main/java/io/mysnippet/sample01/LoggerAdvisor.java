package io.mysnippet.sample01;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggerAdvisor {

  @Advice.OnMethodEnter
  public static void onMethodEnter(
      @Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {

    if (method.getAnnotation(Log.class) != null) {
      System.out.println(
          "Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
    }
  }

  @Advice.OnMethodExit(onThrowable = Throwable.class)
  public static void onMethodExit(
      @Advice.Origin Method method,
      @Advice.AllArguments Object[] arguments,
      @Advice.Return Object ret,
      @Advice.Thrown Throwable t) {

    if (method.getAnnotation(Log.class) != null) {
      System.out.println(
          "Exit "
              + method.getName()
              + " with arguments: "
              + Arrays.toString(arguments)
              + " return: "
              + ret
              + " throws: "
              + t);
    }
  }
}
