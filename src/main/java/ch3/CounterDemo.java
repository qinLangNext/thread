package ch3;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-06-24 15:34
 **/
public class CounterDemo {
    public static void main(String[] args) {
        int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime
                .getRuntime().availableProcessors();
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CounterThread();
        }

        for (Thread t : threads){
            t.start();
        }
    }


    static class CounterThread extends Thread {

        @Override
        public void run() {
            Counter instance = Counter.getInstance();
            instance.increment();
            // System.out.printf("%s got requestID: %s %n",
            //         Thread.currentThread().getName(), instance.vaule());
        }
    }
}
