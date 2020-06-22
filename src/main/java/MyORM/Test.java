package MyORM;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname Test
 * @Description TODO
 * @Date 6/8/20 21:30
 * @Created by mcy
 */

@MyAnnotation
public class Test {
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public String ok() {
        return "ok";
    }

    @SuppressWarnings("all")
    public String no() {
        List<Integer> list = new ArrayList<Integer>();
        List list1 = new ArrayList();
        return "ok";
    }


    @SuppressWarnings({"unchecked",""})
    public static void main(String[] args) throws InterruptedException {

        Date endTime = new Date(System.currentTimeMillis() + 10* 1000);
        long end = endTime.getTime();
        while (true) {
            System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
            endTime = new Date(endTime.getTime() - 1000);
            Thread.sleep(1000);
            if (end - 10*1000 > endTime.getTime()) {
                break;
            }
        }
        System.out.println("------");
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, new Date(System.currentTimeMillis() + 1000), 200);
    }
}
