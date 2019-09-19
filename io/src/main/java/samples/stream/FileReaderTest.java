package samples.stream;

import samples.util.FilePathUtils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 字符流对象实质上操作的还是字节，只不过在于一次操作字节的个数的不同：
 *
 * <p>由具体的字符集决定,GBK一次操作两个字节,UTF-8根据头信息确定读取几个字节。
 *
 * <p>FileReader读取文件首先判断当前jvm的平台字符集，然后通过该字符集的特定信息
 *
 * <p>判断读取一个字符的时候包含几个字节。
 *
 * @author wangyongtao
 * @date 2012-7-3 15:11:59
 */
public class FileReaderTest {

  public static void main(String[] args) throws IOException {


    FileReader reader = new FileReader(FilePathUtils.getResourcePath("汉UTF-16.txt"));

    int size;
    // 数据在内存(bufferChar)中只能以二进制形式存储。
    char[] bufferChar = new char[64];
    for (; (size = reader.read(bufferChar)) > 0; ) {
      System.out.println(new String(bufferChar, 0, size));
    }
    System.out.println("JRE: " + System.getProperty("java.version"));
    /** 如果要避免乱码出现，需要在该类上右键-> run as...-> run configurations... -> common -> 配置编码为 EUC-KR */
    System.out.println("JVM Character: " + Charset.defaultCharset());
  }
}
