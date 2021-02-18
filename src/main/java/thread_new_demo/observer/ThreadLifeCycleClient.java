package thread_new_demo.observer;

import java.util.Arrays;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 16:07
 **/
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
