package thread_new_demo.wait_notify;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 18:23
 **/
public class ProducerConsumer {
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;
    private int i = 0;

    public void produce() {
        synchronized (LOCK) {

            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                isProduced = true;
                LOCK.notify();
            }

        }
    }

    public void consume() {
        synchronized (LOCK) {

            if (isProduced) {
                System.out.println("C->" + i);
                isProduced = false;
                LOCK.notify();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        ProducerConsumer p = new ProducerConsumer();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.produce();

                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.consume();

                }
            }
        }.start();
    }

}
