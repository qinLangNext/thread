package thread_new_demo.observer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 15:52
 **/
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
