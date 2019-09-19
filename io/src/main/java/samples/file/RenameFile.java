package samples.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 把文件命中的商密替换掉
 *
 * @author wangyongtao
 * @date 2014-8-28 上午9:26:59
 */
public class RenameFile {

  public static void rename(File file) {

    // 过滤出dir中以.exe结尾的文件。
    File[] secrets =
        file.listFiles(
            new FilenameFilter() {
              // dir - 被找到的文件所在的目录。name - 文件的名称
              public boolean accept(File dir, String name) {
                // return name.toLowerCase().endsWith(".exe");
                return name.contains("商密");
              }
            });

    for (File source : secrets) {
      // System.out.println(source.getAbsolutePath());
      File dest =
          new File(source.getParent() + File.separator + source.getName().replace("商密", ""));
      // System.out.println(dest.getAbsolutePath());
      source.renameTo(dest);
    }
  }
}
