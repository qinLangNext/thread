package thread_new_demo.worker_thread;

/**
 * 流水线工人模式
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 14:14
 **/
public class WorkerClient {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorker();
        new TransportThread("A", channel).start();
        new TransportThread("B", channel).start();
        new TransportThread("C", channel).start();
        new TransportThread("D", channel).start();
        // new TransportThread("E", channel).start();
        // new TransportThread("F", channel).start();
        // new TransportThread("G", channel).start();
        // new TransportThread("H", channel).start();
    }
}
