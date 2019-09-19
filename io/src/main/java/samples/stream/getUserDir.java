package samples.stream;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 获得系统的信息，通过System.getProperties()得到的属性值中，其中user.dir表示虚拟机启动的路径，
 *
 * <p>java 程序中的相对路径就是相对于此。
 *
 * @author wangyongtao
 * @date 2012-7-4 16:34:59
 */
public class getUserDir {

  public static void main(String[] args) {
    Properties properties = System.getProperties();
    Set set = properties.entrySet();
    Iterator it = set.iterator();

    while (it.hasNext()) {
      Entry entry = (Entry) it.next();
      String key = (String) entry.getKey();
      String value = (String) entry.getValue();
      System.out.println(key + "=" + value);
    }
  }
}
