package MyThread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname test
 * @Description TODO
 * @Date 6/14/20 10:36
 * @Created by mcy
 */


/**
 * 与synchronized 类似的枷锁的加锁
 * ReentrantLock lock = new ReentrantLock(true);
 * lock.lock();
 * try {
 * <p>
 * } finally {
 * lock.unlock();
 * }
 */
public class lock {
    public static void t(Object object) {
        System.out.println(object);
    }

    public static void main(String[] args) {
        DaemonRunner d = new DaemonRunner();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        Thread t3 = new Thread(d);
        Thread t4 = new Thread(d);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }

    }

    static class DaemonRunner implements Runnable {
        private int num = 100;

        Lock locks = new ReentrantLock();

        Object o = new Object(); // default is null

        @SneakyThrows
        @Override
        public void run() {

            while (true) {

                synchronized (o) { // 牺牲效率为前提
                    if (num > 0) {
                        Thread.sleep(10);
                        System.out.println(Thread.currentThread().getName() + "--- num = " + num--);


                    }

                }

//                if (locks.tryLock(5, TimeUnit.SECONDS)) {
//                    if (num > 0) {
//                            Thread.sleep(10);
//                            System.out.println(Thread.currentThread().getName() + "--- num = " + num--);
//                        }
//
//                    locks.unlock();
//                }
//

//                    locks.lock();
//                    try {
//                        if (num > 0) {
//                            Thread.sleep(10);
//                            System.out.println(Thread.currentThread().getName() + "--- num = " + num--);
//                        }
//                    } finally {
//                        locks.unlock();
//                    }
//

            }
        }
    }
}