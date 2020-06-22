package MyJVM;

/**
 * @Classname Test
 * @Description TODO
 * @Date 6/11/20 11:50
 * @Created by mcy
 */
public class Test {
    public static void main(String[] args) throws Exception {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("/Users/mcy/Desktop");
        Class<?> c = fileSystemClassLoader.loadClass("MyJVM.HelloWorld");
        System.out.println(c);
        // 在JVM中不同加载器加载的同一个类，被看作是不同类
    }
}
