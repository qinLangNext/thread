package thread_new_demo.worker_thread;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 14:06
 **/
public class TransportThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
