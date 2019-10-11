package samples.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention 保持力,RetentionPolicy保持策略
 * RetentionPolicy.SOURCE 将其标记的对象信息保持到编译期。
 * RetentionPolicy.CLASS 将其标记的对象信息保持到编译期, 且在class档案中仍然保持,但在运行期会丢掉;
 * RetentionPolicy.RUNTIME 将其标记的对象信息保持到运行期, 信息被保持的运行期的话,我们就可以通过反射机制动态获取;
 *
 * @Targer 指定其所修饰的注解可以修饰哪些元素
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface FoobarAnnotation {

  String foo() default "foo";

  String bar();
}
