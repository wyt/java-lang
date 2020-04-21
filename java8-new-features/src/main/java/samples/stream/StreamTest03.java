package samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * https://www.cnblogs.com/keeya/p/11306254.html
 *
 * <p>跳出forEach替代思路
 *
 * @author wangyongtao
 * @date 2020/4/21
 */
public class StreamTest03 {

  public static void main(String[] args) {
    case1();
  }

  public static void case1() {
    List<String> list = new ArrayList();
    list.add("a");
    list.add("b");
    list.add("c");

    Optional<String> optional =
        list.stream()
            .filter(
                s -> {
                  System.out.println("s=" + s);
                  return s.equals("b");
                })
            .findAny(); // 找到满足的条件即停止

    optional.ifPresent(System.out::println);
  }

  public static void case2() {

    List<String> list = new ArrayList();
    list.add("a");
    list.add("b");
    list.add("c");

    list.stream()
        .anyMatch( // anyMatch()里接收一个返回值为boolean类型的表达式，只要返回true就会终止循环
            s -> {
              System.out.println("do something");
              System.out.println("s=" + s);
              return s.equals("b");
            });
  }
}
