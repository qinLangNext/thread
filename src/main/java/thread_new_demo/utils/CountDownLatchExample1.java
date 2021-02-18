package thread_new_demo.utils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {
    private static Random random = new Random(System.currentTimeMillis());
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    private static CountDownLatch latch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        int[] data = query();
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i, latch));
        }
        latch.await();
        System.out.println("all work finished.");
        executor.shutdown();
    }

    static class SimpleRunnable implements Runnable {
        private final int[] data;
        private final int index;
        private final CountDownLatch latch;

        public SimpleRunnable(int[] a, int index, CountDownLatch latch) {
            this.data = a;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            latch.countDown();

        }
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6};
    }
}
