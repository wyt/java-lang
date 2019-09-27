package samples.callback;

/**
 * @author WANG YONG TAO
 * @date 2019/09/27
 */
public class Test {

  public static void main(String[] args) {
    CustomerService afterSale = new CustomerService();
    Customer consumer = new Customer(afterSale);
    consumer.consult("流量使用情况");
  }
}
