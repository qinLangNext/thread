package thread_new_demo.guarded_suspension;

/**
 * 服务端只需要一直做自己的事情，客户端发送的请求都放到队列当中，
 * 不需要服务端停下当前的任务
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 16:32
 **/
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "guardedSuspension").start();
        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();

        Thread.sleep(10_000);
        serverThread.close();
    }
}
