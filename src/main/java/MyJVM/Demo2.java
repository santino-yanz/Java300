package MyJVM;

/**
 * @Classname Demo2
 * @Description TODO 类加载器负责将class文件字节码内容加载到内存中
 *                   加载资源文件
 *
 *              种类：bootstrap(c) extension application  custom
 * @Date 6/11/20 10:35
 * @Created by mcy
 */


public class Demo2{
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent()); // 由原生代码生成  java获取不到

        System.out.println(System.getProperty("java.class.path"));  // 获取当前系统指定的classpath

        // 交给其他加载器加载指定的类 代理模式
        // 某个特定的加载器 再接到加载类的请求 先优先加载父类加载器，不断着父类  双亲委托机制（代理模式的特殊情况） 核心 保证java核心库的类型安全
        String a = "xd";
        System.out.println(a.getClass().getClassLoader());  // 双亲委派 bootstrap 实际加载的是jdk中的核心库 rt.jar 中的核心类
        System.out.println(a);
        /**
         * tomcat中也使用代理模式 流程：子类中加载没有类加载器 调用父类的类加载器 递归
         */



    }
}
