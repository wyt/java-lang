package samples.stream;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Strem排序
 *
 * @author wangyongtao
 * @date 2020/6/19
 */
public class StreamTest04 {

  public static void main(String[] args) {

    List<Map<String, Object>> list = new ArrayList<>();
    list.add(genMap(new Object[] {"APP端", 10.23, 8.56, 7.01, 8.23}));
    list.add(genMap(new Object[] {"前 端", 23.52, 0.72, 30.32, 6.7}));
    list.add(genMap(new Object[] {"服务端", 8.09, Double.NaN, 23.01, 8.23}));
    list.add(genMap(new Object[] {"中后台", 1.46, 9.98, 7.03, 82}));
    list.add(genMap(new Object[] {"前 端", 23.52, 0.72, 30.32, 6.7}));
    list.add(genMap(new Object[] {"APP端", 10.23, 8.56, 7.01, 8.23}));
    System.out.println("---------------排序前----------------");

    out(list);
    System.out.println("---------------数值列-排序后----------------");

    List<Map<String, Object>> list2 =
        list.stream()
            .sorted(
                Comparator.comparingDouble(
                        new ToDoubleFunction<Map<String, Object>>() {
                          @Override
                          public double applyAsDouble(Map<String, Object> value) {
                            return (double) value.get("col_2");
                          }
                        })
                    .reversed()) // 降序
            .collect(Collectors.toList());

    out(list2);
    System.out.println("--------------字符串列-排序后----------------");

    //    // 方式1 - 匿名函数
    //    list.stream()
    //        .sorted(
    //            new Comparator<Map<String, Object>>() {
    //              @Override
    //              public int compare(Map<String, Object> o1, Map<String, Object> o2) {
    //                return ((String) o1.get("col_0")).compareTo((String) o2.get("col_0"));
    //              }
    //            });
    //
    //    // 方式2 - lambda
    //    list.stream()
    //        .sorted((o1, o2) -> ((String) o1.get("col_0")).compareTo((String) o2.get("col_0")));

    // 方式3 - lambda
    List<Map<String, Object>> list3 =
        list.stream()
            .sorted(Comparator.comparing(p -> ((String) p.get("col_0")), Comparator.reverseOrder()))
            .collect(Collectors.toList());

    out(list3);
  }

  private static void out(List<Map<String, Object>> list) {
    list.forEach(
        map -> {
          map.forEach(
              (k, v) -> {
                System.out.print(v + "\t");
              });
          System.out.println();
        });
    System.out.println();
  }

  private static Map<String, Object> genMap(Object[] args) {

    Map<String, Object> m = new LinkedHashMap<>();

    for (int i = 0; i < args.length; i++) {
      m.put("col_" + i, args[i]);
    }

    return m;
  }
}
