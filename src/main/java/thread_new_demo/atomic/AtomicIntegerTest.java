package thread_new_demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        boolean b = i.compareAndSet(12, 20);
        System.out.println(b);
        System.out.println(i.get());
    }
}
