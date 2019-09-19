package samples.random;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
public class RandomAccessFileTest02 {

  /**
   * 这个方法有问题，会覆盖数据.
   *
   * @param args
   * @throws IOException
   */
  public static void main2(String[] args) throws IOException {
    RandomAccessFile raf =
        new RandomAccessFile("05. io/org/xiaotao/javase/io/random/urls.txt", "rw");

    // 获取RandomAccessFile对象文件指针的位置，初始位置是0
    System.out.println("文件指针的初始位置：" + raf.getFilePointer());

    String str01 = "http://www.eshop.unicom.local";
    // System.out.println(str01.getBytes().length);
    raf.seek(str01.getBytes().length);
    raf.write("<<ADD>>".getBytes());
    raf.close();
  }

  public static void main(String[] args) {
    String str01 = "http://www.eshop.unicom.local";
    beiju(str01.getBytes().length, "<<ADD啊哈啊哈>>", "05. io/org/xiaotao/javase/io/random/urls.txt");
  }

  /**
   * 用RandomAccessFile在文件指定位置插入数据
   *
   * @param skip 跳过多少过字节进行插入数据
   * @param str 要插入的字符串
   * @param fileName 文件路径
   */
  public static void beiju(long skip, String str, String fileName) {
    try {
      RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
      if (skip < 0 || skip > raf.length()) {
        System.out.println("跳过字节数无效");
        return;
      }

      byte[] b = str.getBytes();

      raf.setLength(raf.length() + b.length);
      for (long i = raf.length() - 1; i > b.length + skip - 1; i--) {
        raf.seek(i - b.length);
        byte temp = raf.readByte();
        raf.seek(i);
        raf.writeByte(temp);
      }
      raf.seek(skip);
      raf.write(b);
      raf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
