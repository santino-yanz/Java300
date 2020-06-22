package MyByteCode;

/**
 * @Classname demo1
 * @Description TODO Java动态性 实现方式： 字节码操作和反射机制
 * @Date 6/10/20 23:01
 * @Created by mcy
 */


import javassist.*;

import java.io.IOException;
/**
 * 运行时操作字节码的优势 比反射开销小 性能高
 *  JavaAssist库     AOP 通过字节码动态的给语句的前后增加新方法代码
 *  动态创建类对象
 */
public class demo1 {
    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {

        // 获得类池 从类池中调出ctClass
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("MyByteCode.Employee");

        // 创建属性
        CtField ctField = CtField.make("private String name;", ctClass);
        CtField ctField1 = CtField.make("private int age;", ctClass);

        ctClass.addField(ctField);
        ctClass.addField(ctField1);

        // 创建方法
        CtMethod ctMethod = CtMethod.make(" public String getName() { return name;}", ctClass);
        CtMethod ctMethod1 = CtMethod.make(" public void setAge(int age) { this.age = age;}", ctClass);
        ctClass.addMethod(ctMethod);
        ctClass.addMethod(ctMethod1);

        // 添加构造器  基本数据类型和一般数据类型的get区别
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), CtClass.intType}, ctClass);
        ctConstructor.setBody("{this.name = name; this.age = age;}"); // 构造器的主体
        ctClass.addConstructor(ctConstructor); // 添加构造器

        ctClass.writeFile("/Users/mcy/Desktop/cs/Java300/src/main/java/MyByteCode/"); // 将上面编写好的类写入到工作空间

    }
}

