package thread_new_demo.guarded_suspension;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 15:45
 **/
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                   return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request){
        synchronized(queue){
            queue.add(request);
            queue.notifyAll();
        }
    }
}
