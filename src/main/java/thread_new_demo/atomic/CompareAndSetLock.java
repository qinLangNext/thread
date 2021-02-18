package thread_new_demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean flag = value.compareAndSet(0, 1);
        if (!flag){
            throw new GetLockException("Get the lock failed.");
        }else {
            lockedThread = Thread.currentThread();
        }
    }

    public void unlock(){
        if (value.get() == 0){
            return;
        }
        if (lockedThread == Thread.currentThread())
        value.compareAndSet(1, 0);
    }
}
