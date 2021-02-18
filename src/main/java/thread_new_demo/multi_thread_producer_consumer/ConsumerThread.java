package thread_new_demo.multi_thread_producer_consumer;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 11:16
 **/
public class ConsumerThread extends Thread {
    private final MessageQueue queue;
    private final Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue queue, int seq) {
        super("CONSUMER-"+seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            Message message = queue.take();
            System.out.println(Thread.currentThread().getName()+" consumer a message "+ message.getData());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
