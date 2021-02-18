package thread_new_demo.future;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 10:48
 **/
public interface Future<T> {
    T get() throws InterruptedException;
}
