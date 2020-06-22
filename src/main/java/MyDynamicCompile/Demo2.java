package MyDynamicCompile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Classname Demo2
 * @Description TODO
 * @Date 6/10/20 21:44
 * @Created by mcy
 */
public class Demo2 {
    // 通过反射机制 类加载器 动态编译运行类
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        URL[] urls = new URL[] {new URL("file:"+"/Users/mcy/Desktop/") };
        URLClassLoader loader = new URLClassLoader(urls);
        Class c = loader.loadClass("HelloWorld");
        // 调用加载类的main方法
        Method method = c.getMethod("main", String[].class);
        // 静态方法 obj为null
        method.invoke(null, (Object)new String[]{}); // 如果不转为Object类型 5.0后按照可变类型参数处理


    }
}
