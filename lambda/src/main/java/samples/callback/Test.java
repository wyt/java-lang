package samples.callback;

public class Test {

  public static void main(String[] args) {
    AfterSaleService afterSale = new AfterSaleService();
    Consumer consumer = new Consumer(afterSale);
    consumer.consult("请问设备不工作怎么办呀？");
  }
}
