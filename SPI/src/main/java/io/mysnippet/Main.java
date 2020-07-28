package io.mysnippet;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wangyongtao
 * @date 2020/7/28
 */
public class Main {

  public static void main(String[] args) {

    ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
    Iterator<Log> iterator = serviceLoader.iterator();

    while (iterator.hasNext()) {
      Log logger = iterator.next();
      logger.log("Hello, SPI!");
    }
  }
}
