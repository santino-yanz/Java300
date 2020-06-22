package MyThread;

import lombok.SneakyThrows;

/**
 * @Classname PC_Multi
 * @Description TODO 多生产者多消费者模式
 * @Date 6/14/20 21:33
 * @Created by mcy
 */

/**
 * 出现的问题：同一个产品 多次消费   解决将if改成while
 * Thread-1---生产了娃哈哈4258
 * Thread-1---生产了娃哈哈4259
 * Thread-3---消费了 娃哈哈4259
 * Thread-2---消费了 娃哈哈4259
 * Thread-2---消费了 娃哈哈4259
 * Thread-3---消费了 娃哈哈4259
 * Thread-3---消费了 娃哈哈4259
 */
public class PC_Multi {

    public static void main(String[] args) {
        ProducerCustomer pc = new ProducerCustomer();
        M_Producer m_producer = new M_Producer(pc);
        M_Customer m_customer = new M_Customer(pc);

        Thread t0 = new Thread(m_producer);
        Thread t1 = new Thread(m_producer);

        Thread t2 = new Thread(m_customer);
        Thread t3 = new Thread(m_customer);

        t0.start();
        t1.start();

        t2.start();
        t3.start();



    }


}

/**
 * 生产者
 */
class M_Producer implements Runnable{

    private ProducerCustomer pc;

    public M_Producer(ProducerCustomer pc) {
        this.pc = pc;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            pc.product("娃哈哈");
        }
    }
}

/**
 * 消费者
 */
class M_Customer implements Runnable {
    private ProducerCustomer pc;

    public M_Customer(ProducerCustomer pc) {
        this.pc = pc;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            this.pc.custom();
        }
    }
}


class ProducerCustomer {
    private String name;
    private int count = 1;
    private boolean flag = false;

    /**
     * produce
     */
    @SneakyThrows
    public synchronized void product (String name) {
        while (flag) this.wait(); // true 等待消费
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
        while (!flag) this.wait();
        System.out.println(Thread.currentThread().getName() + "---消费了 " + this.name);
        flag = !flag;
        this.notifyAll();
    }
}

