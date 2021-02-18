package thread_new_demo.future;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 10:52
 **/
public class AsynFuture<T> implements Future<T> {
    private volatile boolean done = false;

    private T result;

    public void done(T result){
        synchronized (this){
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while (!done){
                this.wait();
            }
        }
        return result;
    }
}
