package samples.character;

import java.io.InputStream;

/**
 * jvm内存中的字符串始终以UTF-16编码。
 *
 * @author wangyongtao
 * @date 2012-7-7 21:20:53
 */
public class JvmDefaultCharacter {

  public static void main(String[] args) throws Exception {

    String str = "";
    InputStream in = JvmDefaultCharacter.class.getClassLoader().getResourceAsStream("汉GBK.txt");
    byte[] cbuf = new byte[5]; //
    int hasRead = 0;
    while ((hasRead = in.read(cbuf)) > 0) {
      // 将字符串从gbk码 转换到 unicode码
      str = new String(cbuf, 0, hasRead, "GBK");
    }

    char[] strCharArr = str.toCharArray();
    for (char c : strCharArr) {
      System.out.format("%x", (int) c);
    }
  }
}
