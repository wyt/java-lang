package samples.simple;

/**
 * 使用枚举类型定义注解属性。
 *
 * @author wangyongtao
 */
public @interface BarAnnotation {

  Grade[] value();
}
