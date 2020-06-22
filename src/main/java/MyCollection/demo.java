package MyCollection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname demo
 * @Description TODO
 * @Date 6/11/20 22:14
 * @Created by mcy
 */
public class demo {

    private static Set set;
    private static List list;
    private static HashMap hashMap;
    private static HashSet hashSet;
    private static ConcurrentHashMap concurrentHashMap;
    private static Vector vector; // 线程安全 效率低

    public static void testList() {
        list = new ArrayList();
        list.add("abc");
        list.add(new Date());
        list.add(10); // 自动装箱
        System.out.println(list.size());
        System.out.println(list.contains(1));
        System.out.println(list.isEmpty());
        System.out.println(list.remove("abc"));


        list.add(new ArrayList<Integer>(20));  // 这里的20表示初始化时每个元素的大小

        System.out.println(list.size());
        System.out.println(list.remove(1));
        System.out.println(list.remove(new Date()));

        System.out.println(list.size());
        System.out.println(list);

        list = new LinkedList();


    }


    public static void main(String[] args) {
        testList();
    }

}
