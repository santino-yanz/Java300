package MyReflection;

/**
 * @Classname Demo4
 * @Description TODO 反射机制的性能问题 弊端：使程序变慢
 *               setAccessible(true)  禁用安全检查 可以提高运行效率
 *               比用安全检查 提高4倍
 * @Date 6/9/20 15:33
 * @Created by mcy
 */


import Bean.User;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 范型仅仅是给编译器javac使用的，确保数据的安全性和免去强制类型转换的麻烦
 * 由于类编译加载到虚拟机完成后， 范型会被擦除 反射机制就不能操作范型
 *
 * -- 如何反射获取范型信息
 */
public class Demo4 {

    public void test01(Map<String, User> map, List<User> list) {
        System.out.println("Demo4.test01()");
    }


    public  Map<Integer, User> test02() {
        System.out.println("Demo4.test02()");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        //  获得指定方法参数的范型信息
        Method method = Demo4.class.getMethod("test01", Map.class, List.class);
        Type[] types = method.getGenericParameterTypes();
        for (Type para :
                types) {
            System.out.println("xxxxxx    " + para);
            if (para instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) para).getActualTypeArguments();

                for (Type genericType :
                        genericTypes) {
                    System.out.println("ooooooo   " + genericType);
                }
            }
        }

        // 获得指定方法返回值的范型信息
        Method method1 = Demo4.class.getMethod("test02", null);
        Type type = method1.getGenericReturnType();

        if (type instanceof ParameterizedType) {
            System.out.println();
            Type[] types1 = ((ParameterizedType) type).getActualTypeArguments();
            for (Type t :
                    types1) {
                System.out.println(t);
            }
        }

    }

}
