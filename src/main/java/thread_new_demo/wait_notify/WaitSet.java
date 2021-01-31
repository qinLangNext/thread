package thread_new_demo.wait_notify;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {
    private final static Object LOCK = new Object();

    /**
     * 1. 所有对象存在wait set方法，用来存放调用该对象wait方法之后进入block状态线程
     * 2. 线程notify后，不一定立即执行
     * 3. wait set 中被唤醒的线程不一定执行FIFO
     * 4. 线程唤醒后，必须重新获取锁
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        synchronized (LOCK) {
                            try {
                                Optional.of(Thread.currentThread().getName() + "will come to wait set.")
                                        .ifPresent(System.out::println);
                                LOCK.wait();
                                Optional.of(Thread.currentThread().getName() + "will leave to wait set.")
                                        .ifPresent(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    synchronized (LOCK) {
                        LOCK.notify();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
