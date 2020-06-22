package MyByteCode;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * @Classname demo3
 * @Description TODO frozen classes 要使用首先要解冻
 * @Date 6/11/20 03:39
 * @Created by mcy
 */

/**
 *   注解操作
 */

// 使用注解
public class demo3 {

    public static void main(String[] args) throws Exception{
        CtClass ctClass = ClassPool.getDefault().get("MyByteCode.Employee");
        Object[] all = ctClass.getAnnotations();
        Author author = (Author) all[0];

        String name = author.name();
        int year = author.year();
        System.out.println(name);
        System.out.println(year);


    }
}
