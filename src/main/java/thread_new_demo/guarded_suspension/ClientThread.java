package thread_new_demo.guarded_suspension;

import java.util.Random;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 16:03
 **/
public class ClientThread extends Thread {
    private final RequestQueue requestQueue;
    private final Random random;
    private final String sendValue;
    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.requestQueue = requestQueue;
        this.sendValue = sendValue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i=0; i< 10; i++){
            System.out.println("Client Request -> "+sendValue);
            requestQueue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
