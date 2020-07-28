package io.mysnippet.myloder.demo;

/**
 * @author wangyongtao
 * @date 2020/7/22
 */
public class MyClassLoader extends ClassLoader {

  public MyClassLoader(ClassLoader classLoader) {
    super(classLoader);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    Class<?> loadedClass = findLoadedClass(name);
    return null;
  }
}
