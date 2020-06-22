package MyJVM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Classname Demo3
 * @Description TODO 自定义加密解密加载器
 * @Date 6/11/20 12:17
 * @Created by mcy
 */

public class EncryptClassLoader {

    public static void encrypt(String root, String dest) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(root);
            fileOutputStream = new FileOutputStream(dest);

            int temp = -1;
            while (-1 != (temp = fileInputStream.read())) {
                fileOutputStream.write(temp ^ 0xff); // 对读到的数据取反
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public static void main(String[] args) throws Exception {
        int a = 3;
        System.out.println(Integer.toBinaryString(a ^ 0xff)); // 异或取反

        // 测试加密的class文件
       encrypt("/Users/mcy/Desktop/HelloWorld.class", "/Users/mcy/Desktop/temp/HelloWorld.class");




        // java.lang.ClassFormatError: Incompatible magic value 889275713 in class file HelloWorld
        FileSystemClassLoader loader = new FileSystemClassLoader("/Users/mcy/Desktop/temp");
        Class<?> c = loader.loadClass("HelloWorld");
        System.out.println(c);

    }


}
