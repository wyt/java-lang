package samples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.runoob.com/java/java8-streams.html<br>
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *
 * @author wangyongtao
 * @date 2019/11/24
 */
public class StreamTest01 {

  public static void main(String[] args) {

    //  为集合创建串行流
    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
    List<String> filtered =
        strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

    filtered.forEach(System.out::println);
  }
}
