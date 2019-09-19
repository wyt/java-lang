package samples.stream;

import java.io.*;

/**
 * 文件的复制。
 *
 * <p>一个输入流对象建立好后，源头就会确定，输入流对象的read方法就会从源头读取内容；
 *
 * <p>一个输出流对象建立好后，目的就会确立，输出流对象的write方法就会把内容写到目的地。
 *
 * @author wangyongtao
 * @date 2012-6-28 22:40:00
 */
@SuppressWarnings("all")
public class FileOutputStreamTest {

  public static void main(String[] args) throws IOException {
    FileInputStream fis = null;
    FileOutputStream fos = null;

    try {
      fis = new FileInputStream("05. io/org/xiaotao/javase/io/stream/storeUTF-8.properties");
      fos = new FileOutputStream("05. io/org/xiaotao/javase/io/stream/storeUTF-8GOPY.properties");

      byte[] cache = new byte[1024];
      int readLength = 0;

      while ((readLength = fis.read(cache)) > 0) {
        // 输出流写出cache中的内容。从0开始，到readLength - 1 个。
        fos.write(cache, 0, readLength);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fis != null) {
        fis.close();
      }
      if (fos != null) {
        fos.close();
      }
    }
  }

  /**
   * 注意写程序先提取公共的方法
   *
   * @param fils 需要拷贝的文件
   * @param dir 拷贝文件的目的地
   * @throws IOException
   */
  public static void copyFiles(File[] fils, String dir) throws IOException {
    InputStream fis = null;
    OutputStream fos = null;

    for (File sourceFile : fils) {
      fis = new FileInputStream(sourceFile);
      fos = new FileOutputStream(dir + File.separator + sourceFile.getName());

      byte[] cache = new byte[1024 * 1024];
      int readLength = 0;

      while ((readLength = fis.read(cache)) > 0) {
        fos.write(cache, 0, readLength);
      }
    }

    fis.close();
    fos.close();
  }
}
