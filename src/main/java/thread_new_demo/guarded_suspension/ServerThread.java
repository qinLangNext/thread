package thread_new_demo.guarded_suspension;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 16:11
 **/
public class ServerThread extends Thread {
    private final RequestQueue requestQueue;
    private final Random random;
    private volatile boolean closed = false;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        random = new Random();
    }

    @Override
    public void run() {
        while (!closed){
            Request request = requestQueue.getRequest();
            if (null == request){
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server ->"+request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
               return;
            }
        }
    }

    public void close(){
        this.closed = true;
        this.interrupt();
    }
}
