package io.mysnippet.sample01;

import io.mysnippet.sample01.Log;

public class Service {

  @Log
  public int foo(int value) {
    System.out.println("foo: " + value);
    return value;
  }

  public int bar(int value) {
    System.out.println("bar: " + value);
    return value;
  }
}
