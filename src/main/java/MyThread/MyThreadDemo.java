package MyThread;

import com.sun.org.apache.xpath.internal.functions.FuncTranslate;
import lombok.SneakyThrows;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Classname My
 * @Description TODO 创建线程方式 extends Thread / implements Runnable / implements Callable
 * @Date 6/10/20 15:56
 * @Created by mcy
 */


/**
 * 线程池的关闭有两种方法的实现  都要调用awaitTermination方法阻塞等待
 * shutdown 线程池拒绝接受新提交任务，同时等待线程池中的任务执行完毕后关闭线程池
 *
 * shutdownNow 线程池拒绝接受新提交的任务， 同时立即关闭线程池，线程池中的任务不再执行
 */
public class MyThreadDemo {

    public static void t1Extends() {

    }

    public static void t2Implement() {

    }

    @SneakyThrows
    public static void t3BIO() {
        /**
         *  extends and implements 属于阻塞式 必须等待当前任务完成 再切换
         *  比如 demo线程执行3秒的任务 和 demo1 线程执行1秒的任务 同时开启
         */
        // extends Thread

        long start = System.currentTimeMillis();
        Demo demo = new Demo();
        demo.start();
        demo.join();

        // implements Runnable
        Demo1 demo1 = new Demo1();  // 静态代理 实现Runnable对象实现run() 并且将run()方法让Thread()去执行
        Thread t1 = new Thread(demo1);
        t1.start();
        t1.join();

        long end = System.currentTimeMillis();
        System.out.println("使用的时间" + (end - start));

    }

    @SneakyThrows
    public static void t4Callable() {

        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        Demo2 calTask = new Demo2();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(calTask);  // 提交子线程
        //关闭线程池 对具体的Runnable或者Callable对象任务执行的结果进行获取(get()),
        // 取消(cancel()),判断是否完成等操作
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if(null != future.get()){
                //输出获取到的结果
                System.out.println("future.get()-->"+future.get());
            }else{
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }


    @SneakyThrows
    public static void t5Callable_FutureTask() {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Demo2 demo2 = new Demo2();

        FutureTask<Integer> futureTask = new FutureTask<>(demo2);
        es.submit(futureTask);
        es.shutdown();

        Thread.sleep(2000);
        System.out.println("main thread do others");
        if (futureTask.get() != null) {
            System.out.println("futureTask.get() -- >" + futureTask.get());

        } else {
            System.out.println("futureTask.get() failure");
        }
        System.out.println("main thread done");

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        t3BIO();

//        t4Callable();
        t5Callable_FutureTask();
    }
}


class Demo extends Thread {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Demo线程正在做，时间3秒");
        Thread.sleep(1000 * 3);

    }
}

class Demo1 implements Runnable {
    @SneakyThrows
    @Override
    public void run() {

        System.out.println("Demo1线程正在做，时间1秒");
        Thread.sleep(1000 * 3);
    }

}

/**
 * 有返回值的线程创建方式
 */

class Demo2 implements Callable<Integer> {
    private String word;
    private int sum;

    public Demo2() {
        super();
    }

    public Demo2(String word) {
        this.word = word;
    }

    @SneakyThrows
    @Override
    public Integer call() {
        System.out.println("子线程Demo2 start");
        Thread.sleep(5000);
        for (int i = 0; i < 5000; i++) {
            sum += i;
        }
        System.out.println("子线程Demo2 end");
        return sum;

    }
}
