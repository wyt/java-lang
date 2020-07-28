package io.mysnippet.impl;

import io.mysnippet.Log;

/**
 * @author wangyongtao
 * @date 2020/7/28
 */
public class Logback implements Log {

  @Override
  public void log(String info) {
    System.out.println("Logback: " + info);
  }
}
