package MyJVM.ThreadContent;

import MyJVM.Demo1;
import MyJVM.FileSystemClassLoader;

/**
 * @Classname demo1
 * @Description TODO 测试线程上下文类加载器  Tomcat中 每一个app都配备一个独立的类加载器
 * @Date 6/11/20 20:10
 * @Created by mcy
 */
public class demo1 {
    public static void main (String[] args) throws ClassNotFoundException {
        ClassLoader loader = demo1.class.getClassLoader();
        System.out.println(loader);

        ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
        System.out.println(loader1);

        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("/Users/mcy/Desktop"));
        System.out.println(Thread.currentThread().getContextClassLoader());


        Class<?> cc = Thread.currentThread().getContextClassLoader().loadClass("HelloWorld");
        Class<Demo1> cc1= (Class<Demo1>)Thread.currentThread().getContextClassLoader().loadClass("MyJVM.Demo1");
        System.out.println(cc.getClassLoader());

        System.out.println(cc1.getClassLoader()); //调用了自定义类中的双亲委派机制
    }
}
