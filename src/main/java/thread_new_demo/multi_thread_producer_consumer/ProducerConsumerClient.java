package thread_new_demo.multi_thread_producer_consumer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 11:21
 **/
public class ProducerConsumerClient {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        new ProducerThread(messageQueue, 1).start();
        new ProducerThread(messageQueue, 2).start();
        new ProducerThread(messageQueue, 3).start();
        new ConsumerThread(messageQueue, 1).start();
        new ConsumerThread(messageQueue, 2).start();
    }
}
