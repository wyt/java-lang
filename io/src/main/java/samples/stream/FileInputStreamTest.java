package samples.stream;

import samples.util.FilePathUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 注意：改变console encoding 的编码，jvm character 也会相应的变化。
 *
 * @author wangyongtao
 * @date 2012-5-15 22:14:32
 */
public class FileInputStreamTest {

  public static void main(String[] args) throws IOException {

    FileInputStream fis = new FileInputStream(FilePathUtils.getResourcePath("hanwen.txt"));

    // 创建一个缓冲区(缓存)bbuf. byte类型只能存储一个8位(bit),即1个字节(Byte)的数据,所以1024长度的byte数组能存储1024B=1KB的数据。
    byte[] bbuf = new byte[1024];
    int hasRead = 0;

    // read(byte[] b)内部实现原理：
    // 其实是read方法内部是由指针控制,每次指针停止时返回实际读取的字节数,造成指针停止
    // 有两种情况:1.每次读取字节的个数正好是字节数组的长度。 2.刚好读到文件末尾标记之前。
    // 下一次再从指针停止的地方继续读,如果读到文件末尾标记,返回-1
    for (; (hasRead = fis.read(bbuf)) > 0; ) {
      for (int i = 0; i < hasRead; i++) {
        System.out.print(bbuf[i] + " ");
      }
      System.out.println();
      System.out.println("JRE: " + System.getProperty("java.version"));
      System.out.println("JVM Character: " + Charset.defaultCharset());

      // hanwen.txt文件是用EUR-KR编码的,所以fis.read(bbuf)读到的是EUR-KR码表中的值,
      // 因此我们在将这些字节数解码时要指定编码所用的码表。
      // 另外如果read()方法读到的字符码值不完整,解码时也会造成乱码。

      // 正确的解码
      System.out.println(new String(bbuf, 0, hasRead, "EUC-KR"));
      // 使用了错误的编码
      System.out.println(new String(bbuf, 0, hasRead, "GBK"));
    }
    fis.close();
  }
}
