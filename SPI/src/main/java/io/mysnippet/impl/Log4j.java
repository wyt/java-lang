package io.mysnippet.impl;

import io.mysnippet.Log;

/**
 * @author wangyongtao
 * @date 2020/7/28
 */
public class Log4j implements Log {

  @Override
  public void log(String info) {
    System.out.println("Log4j: " + info);
  }
}
