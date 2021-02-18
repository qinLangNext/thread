package thread_new_demo.two_phase_termination;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 16:39
 **/
public class CounterIncrement extends Thread {

    private int counter = 0;
    private volatile boolean termination = false;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!termination) {
                System.out.println(Thread.currentThread().getName() + " couter " + this.counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do some clean for the second phase, the counter is "+ this.counter);
    }

    public void close(){
        this.termination = true;
        this.interrupt();
    }
}
