package ch3;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {

    private static Counter counter = new Counter();

    private volatile long count;
    // private volatile AtomicLong count = new AtomicLong(0);

    public long vaule() {

            // return count.get();
            return count;

    }


    public void increment() {
        synchronized (this) {
            // count.incrementAndGet();
            count++;
            System.out.printf("%s got requestID: %s %n",
                    Thread.currentThread().getName(), vaule());
        }
    }

    public static Counter getInstance() {
        return counter;
    }

}
