package thread_new_demo.sync;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 15:28
 **/
public class TicketWindowRunnable implements Runnable {
    private final static int MAX = 500;
    private int index =1;
    private  Object MONITOR = new Object();
    @Override
    public void run(){
        while (true){
            if(index > MAX){
                break;
            }
            try {
                //当前线程休息
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "号码:"+ (index++));
        }
    }
}
