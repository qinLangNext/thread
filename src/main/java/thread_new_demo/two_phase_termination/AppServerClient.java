package thread_new_demo.two_phase_termination;

import java.io.IOException;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 10:58
 **/
public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(13345);
        appServer.start();
        Thread.sleep(20_000);
        appServer.shutdown();
    }
}
