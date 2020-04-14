package samples.optional;

import java.util.Optional;

/**
 * Optional用例
 *
 * @author wangyongtao
 * @date 2019/12/21
 */
public class Foobar {

  public static void main(String[] args) {
    String strA = " abcd ", strB = null;
    print(strA);
    print("");
    print(strB);
    getLength(strA);
    getLength("");
    getLength(strB);
  }

  public static int getLength(String text) {
    // Pre-Java 8
    // return if (text != null) ? text.length() : -1;

    // Java 8
    return Optional.ofNullable(text).map(String::length).orElse(-1);
  }

  public static void print(String text) {
    //    // Pre-Java 8
    //    if (text != null) {
    //      System.out.println(text);
    //    }

    // Java 8
    Optional.ofNullable(text).ifPresent(System.out::println);
  }
}
