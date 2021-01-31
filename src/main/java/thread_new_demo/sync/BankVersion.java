package thread_new_demo.sync;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 15:31
 **/
public class BankVersion {
    public static void main(String[] args) {
        TicketWindowRunnable r = new TicketWindowRunnable();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
    }
}
