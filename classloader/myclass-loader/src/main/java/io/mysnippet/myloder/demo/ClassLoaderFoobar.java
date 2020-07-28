package io.mysnippet.myloder.demo;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 各种类加载器
 *
 * @author WANG YONG TAO
 */
public class ClassLoaderFoobar {

  public static void main(String[] args) throws IOException {

    URL url22 = ClassLoaderFoobar.class.getClassLoader().getResource("logback-agent.xml");
    System.out.println(url22);

    System.out.println("[根类加载器]的加载路径：");
    URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
    for (URL url : urls) {
      System.out.println(url.toExternalForm());
    }

    ClassLoader extClassLoader = ClassLoader.getSystemClassLoader().getParent();
    System.out.println(extClassLoader);
    Enumeration<URL> enums2 = extClassLoader.getResources("");
    System.out.println("[扩展类加载器]的加载路径：");
    while (enums2.hasMoreElements()) {
      System.out.println(enums2.nextElement().getPath());
    }

    ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
    System.out.println(sysClassLoader);
    Enumeration<URL> enums = sysClassLoader.getResources("");
    System.out.println("[系统类加载器]的加载路径：");
    while (enums.hasMoreElements()) {
      System.out.println(enums.nextElement().getPath());
    }

    System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
    // 得到该类的类加载器(普通类加载器) getResource("")只写空串,不写资源名,返回的是类加载器的加载路径部分(用URL类表示)。
    String path =
        ClassLoaderFoobar.class
            .getClassLoader()
            .getResource("io/mysnippet/ClassLoaderFoobar.class")
            .getPath();
    System.out.println("资源的路径为：" + path);
  }
}
