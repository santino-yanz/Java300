package MyORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname MyORMAnnotation
 * @Description TODO
 * @Date 6/9/20 10:57
 * @Created by mcy
 */

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyORM_Table_Annotation {
    String value();
}
