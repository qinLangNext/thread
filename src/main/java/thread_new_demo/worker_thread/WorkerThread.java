package thread_new_demo.worker_thread;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 13:43
 **/
public class WorkerThread extends Thread {
    private static final Random random = new Random(System.currentTimeMillis());
    private final Channel channel;
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            this.channel.take().execute();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
