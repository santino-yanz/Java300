package MyReflection;

import Bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname Demo3
 * @Description TODO 将Demo2中 获得的数据 动态调用起来
 * @Date 6/9/20 15:13
 * @Created by mcy
 */


@SuppressWarnings("unchecked")
public class Demo3 {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        String path = "Bean.User";
        System.out.println("hello");

        Class<User> c = (Class<User>) (new User()).getClass();
        // 通过 反射api 构造对象
        User u = c.newInstance(); //调用User的无参构造器

        // 指定构造器 构造对象

        Constructor<User> constructor = c.getDeclaredConstructor(int.class, String.class, int.class);

        User user = constructor.newInstance(001, "jack", 12);
        System.out.println(user.getName());


        // 通过反射api 动态调用普通方法

        User user1 = c.newInstance();
        Method method = c.getDeclaredMethod("setName", String.class);
        method.invoke(user1, "may"); // 动态调用
        System.out.println(user1.getName());

        // 通过反射api 动态操作属性
        User user2 = c.newInstance();
        Field field = c.getDeclaredField("name");
//        field.set(user2, "moke"); // 直接赋值会出错 因为name是私有属性
        // 通过反射 对private属性赋值
        field.setAccessible(true); // 跳过安全检查，直接访问
        field.set(user2, "moke");

        System.out.println(user2.getName());
        System.out.println(field.get(user2)); // 通过反射直接读取user2的值
    }
}
