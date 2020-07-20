package io.mysnippet.sample01;

public class Service {

  @Log
  public int foo(int value) {
    System.out.println("foo: " + value / 0);
    return value;
  }

  public int bar(int value) {
    System.out.println("bar: " + value);
    return value;
  }
}
