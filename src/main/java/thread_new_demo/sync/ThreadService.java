package thread_new_demo.sync;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 16:38
 **/
public class ThreadService {
    private Thread executeThread;
    // private volatile boolean  finished = false;
    private boolean finished = false;

    public void execute(Runnable task) {

        executeThread = new Thread(() -> {
            Thread runner = new Thread(task);
            runner.setDaemon(true);
            System.out.println("b" + runner.getName());
            runner.start();

            try {
                //防止守护线程还没有来得及执行，执行线程就已经结束了
                runner.join();
                finished = true;

            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        });
        System.out.println("a" + executeThread.getName());
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束他");
                executeThread.interrupt();
                break;
            }
            //如果finished为volatile类型就不需要sleep
            //main线程休息1，切换给其他线程执行
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }
}
