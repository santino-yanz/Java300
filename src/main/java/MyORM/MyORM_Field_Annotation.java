package MyORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname MyORM_Field_Annotation
 * @Description TODO
 * @Date 6/9/20 11:01
 * @Created by mcy
 */

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyORM_Field_Annotation {
    String columnName();
    String type();
    int length();
}
