package samples.stream;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author wangyongtao
 * @date 2012-7-4 16:01:01
 */
@SuppressWarnings("all")
public class FileWriterTest {

  public static void main(String[] args) throws Exception {
    FileReader fr = new FileReader("io/org/xiaotao/javase/io/stream/hanwen.txt");
    FileWriter fw = new FileWriter("io/org/xiaotao/javase/io/stream/hanwen_copy.txt");

    char[] cbuf = new char[1024];
    int len = 0;
    while ((len = fr.read(cbuf)) > 0) {
      fw.write(cbuf, 0, len);
    }
    fw.close();
  }
}
