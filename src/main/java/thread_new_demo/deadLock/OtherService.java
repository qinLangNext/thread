package thread_new_demo.deadLock;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 17:56
 **/
public class OtherService {
    private final Object lock = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1(){
        synchronized (lock){
            System.out.println("s1==========");
        }
    }

    public void s2(){
        synchronized (lock){
            this.deadLock.m2();
        }
    }
}
