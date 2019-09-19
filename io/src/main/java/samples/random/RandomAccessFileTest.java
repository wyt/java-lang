package samples.random;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
public class RandomAccessFileTest {

  public static void main(String[] args) {

    /** 注意try(){}catch(){} 是java 7新特性 */
    try (RandomAccessFile raf =
        new RandomAccessFile(
            "05. io/org/xiaotao/javase/io/random/RandomAccessFileTest.java", "r")) {

      // 获取RandomAccessFile对象文件指针的位置，初始位置是0
      System.out.println("RandomAccessFile的文件指针的初始位置：" + raf.getFilePointer());

      // 移动raf的文件记录指针的位置
      raf.seek(300);
      byte[] bbuf = new byte[1024];

      // 用于保存实际读取的字节数
      int hasRead = 0;
      // 使用循环来重复"取水"过程
      while ((hasRead = raf.read(bbuf)) > 0) {
        // 取出"竹筒"中水滴（字节），将字节数组转换成字符串输入！
        System.out.print(new String(bbuf, 0, hasRead));
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
