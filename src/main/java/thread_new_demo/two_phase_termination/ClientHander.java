package thread_new_demo.two_phase_termination;

import java.io.*;
import java.net.Socket;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 10:28
 **/
public class ClientHander implements Runnable {
    private final Socket socket;
    private volatile boolean running = true;

    public ClientHander(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream)) {
            while (running) {
                String message = br.readLine();
                System.out.println("Come from client =>" + message);
                printWriter.write(message);
                printWriter.flush();
            }
        } catch (IOException e) {
           this.running = false;
        }finally {
            this.stop();
        }
    }

    public void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}
