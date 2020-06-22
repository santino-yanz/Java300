package MyReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Classname Demo2
 * @Description 使用反射机制 获取属性、方法、构造器
 * @Date 6/9/20 13:23
 * @Created by mcy
 */

@SuppressWarnings("unchecked")
public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        String path = "Bean.User";
        System.out.println("hello");

        Class c = Class.forName(path);


        System.out.println(c.getName());
        System.out.println(c.getSimpleName());

        // 获取所有的属性 和 指定属性
        Field[] fields = c.getDeclaredFields();
        Field f = c.getDeclaredField("name");

        for (Field ff :
                fields) {
            System.out.println(ff);
        }

        Method[] methods = c.getDeclaredMethods();
        Method method = c.getDeclaredMethod("setName", String.class); //  由于重载问题，必须传递class对象

        for (Method m :
                methods) {
            System.out.println(m);
        }

        // 获取构造器
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor temp :
                constructors) {
            System.out.println(temp);
        }

        Constructor constructor = c.getConstructor();
        System.out.println(constructor);

        Constructor constructor1 = c.getConstructor(int.class, String.class, int.class);
        System.out.println(constructor1);


    }
}
