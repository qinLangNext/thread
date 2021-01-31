package pattern.eventbus;

import java.util.concurrent.Executor;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 15:47
 **/
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
