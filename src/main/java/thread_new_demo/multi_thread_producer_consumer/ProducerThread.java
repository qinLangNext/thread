package thread_new_demo.multi_thread_producer_consumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 11:09
 **/
public class ProducerThread extends Thread {

    private final MessageQueue queue;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Random random = new Random(System.currentTimeMillis());

    public ProducerThread(MessageQueue queue, int seq) {
        super("PRODUCER-" + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            Message message = new Message("Message-" + counter.getAndIncrement());
            queue.put(message);
            System.out.println(Thread.currentThread().getName()+ " put message "+ message.getData());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
