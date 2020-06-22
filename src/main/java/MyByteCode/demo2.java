package MyByteCode;

/**
 * @Classname demo2
 * @Description TODO
 * @Date 6/11/20 02:34
 * @Created by mcy
 */

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试javassist的api
 */
public class demo2 {
    public static void test1() throws NotFoundException, IOException, CannotCompileException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("MyByteCode.Employee");

//        byte[] bytes = ctClass.toBytecode();
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(ctClass.getName());  //  获取类名
//        System.out.println(ctClass.getSimpleName());
//        System.out.println(ctClass.getInterfaces()); // 获取接口

        // 测试产生新的方法

//        CtMethod ctMethod = CtMethod.make("public int add(int a, int b) {return a+b;}", ctClass);
        CtMethod ctMethod1 = new CtMethod(CtClass.intType, "add",
                new CtClass[] {CtClass.intType, CtClass.intType}, ctClass);

        ctMethod1.setModifiers(Modifier.PUBLIC);
        ctMethod1.setBody("{return $1+$2;}"); // 确定参数位置
        ctClass.addMethod(ctMethod1);

        // 通过反射调用新生成的方法
        Class<Object> c = (Class<Object>) ctClass.toClass();
        Object obj = c.newInstance();  // 通过Employee的无参构造器 创建对象
        Method method = c.getDeclaredMethod("add", int.class, int.class);
        Object result = method.invoke(obj, 200, 300);
        System.out.println(result);
    }

    /**
     * 动态修改原类中的方法
     * @throws
     */
    public static void test2() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("MyByteCode.Employee");

        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello", new CtClass[]{});
        // 在方法前后添加语句
        ctMethod.insertBefore("System.out.println(\"Yoo\");");
        ctMethod.insertAfter("System.out.println(\"emmm...\");");
        // 在指定行添加语句
        ctMethod.insertAt(44, "System.out.println(\"duck\");");


        Class c = ctClass.toClass();
        Object object = c.newInstance();
        Method method = c.getDeclaredMethod("sayHello");
        method.invoke(object);;


    }


    // 添加属性 获取属性
    public static void test3() throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("MyByteCode.Employee");

        CtField ctField = new CtField(CtClass.intType, "salary", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField, "10000"); // 默认值

//        ctClass.getDeclaredMethod("name"); // 获取指定的属性

        //  对添加的属性 添加对一个的set和get方法
        CtMethod ctMethod = CtNewMethod.getter("getSalary", ctField);
        CtMethod ctMethod1 = CtNewMethod.setter("setSalary", ctField);

        ctClass.addMethod(ctMethod);
        ctClass.addMethod(ctMethod1);

    }


    // 添加构造器

    public static void test4() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("MyByteCode.Employee");

        CtConstructor[] ctConstructors = ctClass.getConstructors();
        for (CtConstructor cc :
                ctConstructors) {
            System.out.println(cc.getLongName());

        }
    }

    public static void main(String[] args) throws Exception {
//        test1();

//        test2();

//        test3();

        test4();

    }
}
