package MyThread;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * @Classname Producer_Customer
 * @Description TODO 单生产者消费者模式
 * @Date 6/14/20 20:03
 * @Created by mcy
 */

public class Producer_Customer {
    private String name;
    private int count = 1;
    private boolean flag = false;

    /**
     * produce
     */
    @SneakyThrows
    public synchronized void product (String name) {
        if (flag) this.wait(); // true 等待消费
        this.name = name + count++;

        System.out.println(Thread.currentThread().getName() + "---生产了" + this.name);
        flag = !flag;
        this.notifyAll();
    }

    /**
     * custom
     */
    @SneakyThrows
    public synchronized void custom() {
        if (!flag) this.wait();
        System.out.println(Thread.currentThread().getName() + "---消费了 " + this.name);
        flag = !flag;
        this.notifyAll();
    }
}


class Single_Producer_Consumer {

    public static void main(String[] args) {
        Producer_Customer pc = new Producer_Customer();
        Producer producer = new Producer(pc);
        Consumer consumer = new Consumer(pc);
//        Consumer consumer1 = new Consumer(pc);

        Thread t0 = new Thread(producer);
        Thread t1 = new Thread(consumer);
//        Thread t2 = new Thread(consumer1);

        t0.start();
        t1.start();
//        t2.start();

    }
}


@Data
class Producer implements Runnable {
    private Producer_Customer pc;

    public Producer(Producer_Customer pc) {
        this.pc = pc;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        while (true) {
            this.pc.product("娃哈哈");
            Thread.sleep(1000);
        }
    }
}

@Data
class Consumer implements Runnable {
    private Producer_Customer pc;

    public Consumer(Producer_Customer pc) {
        this.pc = pc;
    }

    @SneakyThrows
    @Override
    public void run() {

        while (true) {
            this.pc.custom();
            Thread.sleep(1000);
        }
    }
}