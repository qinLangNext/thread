package thread_new_demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> update = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    int MAX = 20;

                    for (int i = 0; i < MAX; i++) {
                        int u = update.getAndIncrement(me);
                        System.out.println(Thread.currentThread().currentThread() + " " + u);
                    }
                }
            }.start();
        }
    }

    /**
     * 1. 想让类的属性具有原子性
     * 2. 类型需一致
     * 3. 不想使用synchronized
     * 4. 大量需要原子类型修饰的对象，消耗内存。
     */
    static class TestMe {
        volatile int i;
    }
}
