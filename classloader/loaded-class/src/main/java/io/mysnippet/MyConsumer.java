package io.mysnippet;

/**
 * @author wangyongtao
 * @date 2020/7/28
 */
public class MyConsumer {

  public static MyProducer myProducer = new MyProducer();

  public void foo() {
    MyProducer producer = new MyProducer();
    producer.produce(10);
  }

  public static void main(String[] args) {
    MyConsumer consumer = new MyConsumer();
    consumer.foo();
  }
}
