package io.mysnippet.myloder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wangyongtao
 * @date 2020/7/27
 */
@SuppressWarnings("all")
public class ProdClassLoader extends ClassLoader {

  private static final String PREFIX = "F:\\tmp\\target\\prod\\";
  private static final String SUFFIX = ".class";

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    Class clazz = null;
    // 将包路径中的点（.）替换成斜线（/）。
    String fileStub = name.replace(".", "\\");
    File classFile = new File(PREFIX + fileStub + SUFFIX);

    if (classFile.exists()) {
      try {
        byte[] raw = getBytes(classFile);
        clazz = defineClass(name, raw, 0, raw.length);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // 如果clazz为null，表明加载失败，则抛出异常
    if (clazz == null) {
      throw new ClassNotFoundException(name);
    }
    return clazz;
  }

  private byte[] getBytes(File file) throws IOException {
    long len = file.length();
    byte[] raw = new byte[(int) len];
    try (FileInputStream fin = new FileInputStream(file)) {
      // 一次读取class文件的全部二进制数据
      int r = fin.read(raw);
      if (r != len) {
        throw new IOException("无法读取全部文件：" + r + " != " + len);
      }
      return raw;
    }
  }
}
