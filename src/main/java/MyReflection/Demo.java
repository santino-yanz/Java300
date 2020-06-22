package MyReflection;

/**
 * @Classname Demo
 * @Description TODO
 * @Date 6/9/20 12:21
 * @Created by mcy
 */
public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        String path = "Bean.User";

        // 通过Class对象 可以看到被加载类的全部信息
        // 同一个类 加载后只有一个Class对象 即反射对象

        Class c = Class.forName(path);
        Class<String> c1 = String.class;
        Class c2 = path.getClass();

        System.out.println(c);
        System.out.println(c.equals(c2));


        int[] a1 = new int[10];
        int[][] a2 = new int[2][2];
        int[] a3 = new int[20];
        double[] a4 = new double[10];

        System.out.println(a1.getClass().hashCode());
        System.out.println(a2.getClass().hashCode());
        System.out.println(a3.getClass().hashCode());
        System.out.println(a4.getClass().hashCode());




    }
}
