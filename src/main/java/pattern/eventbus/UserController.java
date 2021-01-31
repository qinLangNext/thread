package pattern.eventbus;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 15:53
 **/
public class UserController {
    //依赖注入
    private UserService userService;
    private EventBus eventBus;
    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 1;

    public UserController() {
        //同步阻塞模式
        // eventBus = new EventBus();
        //异步非阻塞
        eventBus = new AsyncEventBus(
                Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE, new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        return thread;
                    }
                }));
        userService = new UserService();
    }

    public void setRegObserver(List<Object> obsevers) {
        for (Object obsever : obsevers) {
            eventBus.register(obsever);
        }
    }

    //业务注册
    public Long register(String tel, String pass) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(tel, pass);
        eventBus.post(userId);
        System.out.println("userId: " + userId);
        return userId;
    }
}
