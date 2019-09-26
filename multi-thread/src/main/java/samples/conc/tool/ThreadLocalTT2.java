package samples.conc.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用ThreadLocal优化SimpleDateFormat
 *
 * @author WANG YONG TAO
 * @date 2019/09/26
 */
public class ThreadLocalTT2 {

  //  5.【强制】 SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为
  //  static ，必须加锁，或者使用 DateUtils 工具类。
  //  正例：注意线程安全，使用 DateUtils 。亦推荐如下处理：
  private static final ThreadLocal<DateFormat> DATE_FORMAT =
      ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(
              () -> {
                DateFormat df = DATE_FORMAT.get();
                String formatDate = df.format(new Date());
                System.out.println(formatDate);
              })
          .start();
    }
  }
}
