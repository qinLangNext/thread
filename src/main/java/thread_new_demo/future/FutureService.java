package thread_new_demo.future;

import java.util.function.Consumer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 11:00
 **/
public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task){
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }

    /**
     * 增加回调方式
     * @param task
     * @param consumer
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer){
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        return asynFuture;
    }
}
