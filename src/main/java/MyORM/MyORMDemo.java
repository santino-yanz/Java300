package MyORM;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Classname MyORM
 * @Description TODO
 * @Date 6/9/20 10:52
 * @Created by mcy
 */
public class MyORMDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {

        // 解析程序
        Class c = Class.forName("MyORM.Student");
        Annotation[] annotations =  c.getAnnotations();

        for (Annotation a :
                annotations) {
            System.out.println(a);
        }

        //　获得类的注解
        MyORM_Table_Annotation myORM_table_annotation = (MyORM_Table_Annotation) c.getAnnotation(MyORM_Table_Annotation.class);
        System.out.println(myORM_table_annotation.value());

        // 获得属性的注解
        Field field = c.getDeclaredField("Name");
        MyORM_Field_Annotation myORM_field_annotation = field.getAnnotation(MyORM_Field_Annotation.class);
        System.out.println(myORM_field_annotation.columnName());
        System.out.println(myORM_field_annotation.length());
        System.out.println(myORM_field_annotation.type());




    }
}



@MyORM_Table_Annotation(value = "Test")
class Student {
    @MyORM_Field_Annotation(columnName = "id", type = "int", length = 10)
    private int id;

    @MyORM_Field_Annotation(columnName = "Name", type = "varchar", length = 20)
    private String Name;

    @MyORM_Field_Annotation(columnName = "age", type = "int", length = 3)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}