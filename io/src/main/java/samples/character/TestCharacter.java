package samples.character;

import java.nio.charset.Charset;

/**
 * @author wangyongtao
 * @date 2012-7-5 23:30:33
 */
public class TestCharacter {

  /**
   * 无论是程序内部或者外部读入的字符数据，在jvm内存中都会被转换成Unicode(UTF-16)编码,下面的语句在向控制该打印 "汉" 的时候， 其过程是这样的：
   * 首先"汉"在jvm内存中以Unicode(UTF-16)编码方式存储，out为PrintStream流，该输出流的输出方法会通过字符集
   * 映射表将"汉"的Unicode(UTF-16)编码转换为jvm平台默认的编码(例如GBK)，然后将转换后的编码打印到控制台， 如果控制台编码和jvm平台编码一致，则可以正确的显示出字符数据。
   *
   * @param args
   */
  public static void main(String[] args) {

    String han = "汉"; // 等同于char han = 0x6c49;
    System.out.println(han);

    char[] array = han.toCharArray();
    for (char c : array) {
      // 得到该字符Unicode编码的十六进制表示
      System.out.format("%x", (int) c);

      // 等同于format("%x")
      // System.out.println(Integer.toHexString((int) c));
    }

    System.out.println();
    String encoding = System.getProperty("file.encoding");
    System.out.println("File encoding: " + encoding);
    System.out.println("Jvm encoding: " + Charset.defaultCharset());
  }
}
