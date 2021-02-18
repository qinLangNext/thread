package thread_new_demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * try-lock锁
 */
public class AtomicIntegerTest2 {
    private final static CompareAndSetLock LOCK = new CompareAndSetLock();
    public static void main(String[] args) {
        for (int i=0; i<5; i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        doSomething();
                    } catch (GetLockException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private static void doSomething() throws GetLockException, InterruptedException {
        try {
            LOCK.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock. ");
            Thread.sleep(100_000);
        } finally {
            LOCK.unlock();
        }
    }
}
