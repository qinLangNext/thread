package thread_new_demo.multi_thread_producer_consumer;

import java.util.LinkedList;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 10:50
 **/
public class MessageQueue {
    private final LinkedList<Message> queue;
    private final int limit;
    private final static int DEFAULT_MAX_LIMIT = 100;


    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.queue = new LinkedList<>();
        this.limit = limit;
    }

    public void put(Message message) {
        synchronized (this) {
            while (queue.size() > limit) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            queue.add(message);
            this.notifyAll();
        }
    }

    public Message take() {
        synchronized (this) {
            while (queue.size() <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            Message message = queue.removeFirst();
            this.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageQueueSize() {
        synchronized (this) {
            return queue.size();
        }
    }
}
