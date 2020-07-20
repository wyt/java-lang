package io.mysnippet.sample00;

import java.util.concurrent.TimeUnit;

public class Service {

  public int foo(int value) {
    System.out.println("foo: " + value);
    return value;
  }

  @Duration
  public int bar(int value) {
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("bar: " + value);
    return value;
  }
}
