package MyORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname MyAnnotation
 * @Description 自定义注解
 * 元注解 对注解作解释 @Target   @Retention 注解保留策略 SOURCE CLASS RUNTIME 反射机制可以解析到
 * @Date 6/8/20 23:51
 * @Created by mcy
 */

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    /**
     * 如果只有一个参数 一般定义为 ex: String value()
     *
     * 通过反射解析注解 作用才明显
     * @return
     */

    String studentName() default "";
    int age() default 0;
    int id() default -1; // 表示id不存在

    String[] schools() default {"Tsing", "Peking"};

}
