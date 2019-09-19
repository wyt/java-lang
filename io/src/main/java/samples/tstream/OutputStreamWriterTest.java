package samples.tstream;

import java.io.*;

/**
 * OutputStreamWriter 的构造方法可以将字节输出流对象转换成字符输出流对象，
 *
 * <p>该类实例的write方法有将字符编码为字节的作用。
 *
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
public class OutputStreamWriterTest {
  public static void main(String[] args) throws IOException {
    InputStream is = null;
    InputStreamReader isr = null;
    OutputStream os = null;
    OutputStreamWriter osw = null;
    try {
      is = new FileInputStream("io/org/xiaotao/javase/io/stream/hanwen.txt");
      isr = new InputStreamReader(is, "EUC-KR");
      os = new FileOutputStream("io/org/xiaotao/javase/io/stream/hanwen_copy.txt");
      // 如果不指定osw的字符集,则使用平台(jvm)默认的字符集。
      osw = new OutputStreamWriter(os, "EUC-KR");

      char[] buffer = new char[1024];
      int readLength = 0;
      while ((readLength = isr.read(buffer)) > 0) {
        // osw.write 该方法将buffer中的字符用创建osw对象时指定的字符编码编码为字节写入流中。
        osw.write(buffer, 0, readLength);
      }
      osw.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (is != null) is.close();
      if (isr != null) isr.close();
      if (os != null) os.close();
      if (osw != null) osw.close();
    }
  }
}
