package MyJVM;

/**
 * @Classname Demo1
 * @Description TODO
 * @Date 6/11/20 03:47
 * @Created by mcy
 */
public class Demo1 {
    static {
        System.out.println("demo1");
    }
    public static void main(String[] args) throws ClassNotFoundException {

//        System.out.println("Main ");
//        String str = "aaa";
//        int a = 3333;
//
//        T1 t1 = new T1();
//        System.out.println(T1.x);
//
//        T1 t11 = new T1();  // 类加载的过程只有一次

        /** 类的主动引用 一定会发生类的初始化
         *
         *  new
         *  调用静态属性和方法
         *  反射调用
         *  初始化一个类 先初始化父类
         *
          */


//        System.out.println(T1.x);
//        System.out.println(T1.Y); // final 由于不可再变所以不需要类的初始化
//        Class.forName("MyJVM.T1");

        // 类的被动引用 不会发生类的初始化
//        T1[] t1s = new T1[10]; // 数组类引用 不会触发类的初始化
        System.out.println(T2.x);// 真正被引用的类才会被初始化


    }
}


class T2 extends T1 {
    static {
        System.out.println("static initial T2");
    }
}


class T1 extends T0{
    public static int x=1;

    public static final int Y = 2;

    static {
        System.out.println("static initial class T1");
        x = 2;
    }

    public T1() {
        System.out.println("Create A");
    }
}

class T0 extends Object {
    static {
        System.out.println("TO");
    }
}