package samples.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
public class MyFileUtil {

  public static void FileFilterImpl(File directory, final String suffix) {
    // 过滤目录
    File[] dirs = null;
    // 过滤文件
    File[] files = null;

    dirs =
        directory.listFiles(
            new FileFilter() {
              public boolean accept(File pathname) {
                return pathname.isDirectory();
              }
            });

    files =
        directory.listFiles(
            new FilenameFilter() {
              public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(suffix);
              }
            });
  }
}
