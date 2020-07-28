package io.mysnippet.myloder;

/**
 * @author wangyongtao
 * @date 2020/7/28
 */
@SuppressWarnings("all")
public class FoobarTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {

    ProdClassLoader prodClassLoader = new ProdClassLoader();
    // ConsClassLoader consClassLoader = new ConsClassLoader(); // 报错

    // 不报错，consClassLoader加载不了MyProducer，则交给父类加载器 prodClassLoader加载
    ConsClassLoader consClassLoader = new ConsClassLoader(prodClassLoader);

    Object myConsumer =
        Class.forName("io.mysnippet.MyConsumer", true, consClassLoader).newInstance();

    Object myProducer =
        Class.forName("io.mysnippet.MyProducer", true, prodClassLoader).newInstance();

    System.out.println(myConsumer.getClass().getClassLoader());
    System.out.println(myProducer.getClass().getClassLoader());
  }
}
