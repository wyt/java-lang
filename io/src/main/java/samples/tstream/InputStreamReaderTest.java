package samples.tstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * InputStreamReader 的构造方法可以将字节输入流对象转换成字符输入流对象，
 *
 * <p>该实例的read方法有将字节转成字符的作用。
 *
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
public class InputStreamReaderTest {
  public static void main(String[] args) throws IOException {
    InputStream is = null;
    InputStreamReader isr = null;

    try {
      is = new FileInputStream("io/org/xiaotao/javase/io/stream/hanwen.txt");
      // 指定字符流使用的字符集,如果不指定使用jvm默认的字符编码。
      isr = new InputStreamReader(is, "EUC-KR");

      char[] buffer = new char[1024];
      int readLength = 0;
      while ((readLength = isr.read(buffer)) > 0) {
        System.out.println(new String(buffer, 0, readLength));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (is != null) is.close();
      if (isr != null) isr.close();
    }
  }
}
