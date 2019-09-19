package samples.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wangyongtao
 * @date 2013-7-25 16:25:00
 */
@SuppressWarnings("all")
public class WordUtil {

  public static void main(String[] args) throws IOException {
    BufferedReader br =
        new BufferedReader(new FileReader("io/org/xiaotao/javase/io/stream/wdscb.txt"));
    String line = null;
    while ((line = br.readLine()) != null) {
      if ("+".equals(line.charAt(0) + "")) {
        System.out.println(line.substring(1).trim());
      }
    }
  }
}
