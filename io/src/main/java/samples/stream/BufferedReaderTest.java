package samples.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * BufferedReader readLine 方法可以读取一行。
 *
 * @author wangyongtao
 * @date 2012-7-3 16:49:11
 */
@SuppressWarnings("all")
public class BufferedReaderTest {

  public static void main(String[] args) throws IOException {
    BufferedReader br =
        new BufferedReader(new FileReader("io/org/xiaotao/javase/io/stream/hanwen.txt"));
    String line = null;
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
  }
}
