package MyThread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname PC_Lock
 * @Description TODO 重入锁的实现 可以对一个线程的资源反复的加锁
 * @Date 6/14/20 21:23
 * @Created by mcy
 */
public class PC_Lock {
    private String name;
    private int count = 1;
    private boolean flag = false;

    // 创建锁对象
    private Lock resLock = new ReentrantLock();
    // 创建条件对象
    private Condition condition = resLock.newCondition();

    /**
     * produce
     */
    @SneakyThrows
    public void product (String name) {
        resLock.lock();
        if (flag) condition.await();
        this.name = name + count++;
        System.out.println(Thread.currentThread().getName() + "生产了 " + this.name);
        flag = !flag;
        condition.signalAll();
        resLock.unlock();
    }

    /**
     * custom
     */
    @SneakyThrows
    public synchronized void custom() {
        resLock.lock();
        if (!flag) condition.await();
        System.out.println(Thread.currentThread().getName() + "消费了 " + this.name);
        flag = !flag;
        condition.signalAll();
        resLock.unlock();

    }
}
