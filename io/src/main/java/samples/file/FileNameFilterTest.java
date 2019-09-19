package samples.file;

/**
 * 遍历c盘中的exe文件,看API就能明白。注意windows中有些目录进不去，会报空指针异常
 *
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
import java.io.File;

public class FileNameFilterTest {

  public static void FilterPrintExe(File dir) {
    // 过滤出dir中的目录(即文件夹)
    File[] dirs = dir.listFiles(pathname -> pathname.isDirectory());

    // 过滤出dir中以.exe结尾的文件。
    // dir - 被找到的文件所在的目录。name - 文件的名称,接口方法，参数的用途都事先定义好的
    File[] exes = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".avi"));

    for (File d : dirs) {
      // System.out.println(d);
      FilterPrintExe(d);
    }

    for (File e : exes) {
      System.out.println(e);
    }
  }
}
