package samples.stream;

import java.util.Optional;

/**
 * @author wangyongtao
 * @date 2019/12/18
 */
public class StreamTest02 {

  public static void print(String text) {
    // Java 8
    Optional.ofNullable(text).ifPresent(System.out::println);

    Optional.ofNullable(text)
        .ifPresent(
            t -> {
              System.out.println(t);
            });
    // Pre-Java 8
    if (text != null) {
      System.out.println(text);
    }
  }

  public static int getLength(String text) {
    // Java 8
    return Optional.ofNullable(text).map(String::length).orElse(-1);
    // Pre-Java 8
    // return if (text != null) ? text.length() : -1;
  };
}
