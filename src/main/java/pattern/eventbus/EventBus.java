package pattern.eventbus;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 15:43
 **/
public class EventBus {
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public EventBus() {
    }

    public void register(Object object) {
        System.out.println("event bus register");
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("post....");
                    observerAction.execute(event);
                }
            });
        }
    }

}
