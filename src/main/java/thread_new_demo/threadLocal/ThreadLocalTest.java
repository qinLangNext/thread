package thread_new_demo.threadLocal;

import java.util.Random;

/**
 * ThreadLocal 以当前线程作为key
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 17:26
 **/
public class ThreadLocalTest {
    private final static Random RANDOM = new Random(System.currentTimeMillis());
    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-t1");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-t2");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("============================");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
