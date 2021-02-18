package thread_new_demo.two_phase_termination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 09:52
 **/
public class AppServer extends Thread {
    private int port;
    private static final int DEFAULT_PORT = 12722;
    private volatile boolean start = true;
    private List<ClientHander> clientHandlers = new ArrayList<>();
    private final  ExecutorService executor = Executors.newFixedThreadPool(10);
   private ServerSocket serverSocket;
    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
            while (start) {
                Socket client = serverSocket.accept();
                ClientHander clientHander = new ClientHander(client);
                executor.submit(clientHander);
                this.clientHandlers.add(clientHander);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            this.dispose();
        }

    }

    private void dispose() {
        this.clientHandlers.stream().forEach(ClientHander::stop);

        this.executor.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.serverSocket.close();
    }
}
