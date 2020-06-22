package MyJVM;

/**
 * @Classname TestEncrypt
 * @Description TODO
 * @Date 6/11/20 19:56
 * @Created by mcy
 */
public class TestEncrypt {

    public static void main(String[] args) throws Exception {
        String root = "/Users/mcy/Desktop/HelloWorld.class";
        String dest = "/Users/mcy/Desktop/temp/HelloWorld.class";

        EncryptClassLoader.encrypt(root, dest);

        DecryptClassLoader loader = new DecryptClassLoader("/Users/mcy/Desktop/temp");

        Class<?> c = loader.loadClass("HelloWorld");
        System.out.println(c.getClassLoader());
    }
}
