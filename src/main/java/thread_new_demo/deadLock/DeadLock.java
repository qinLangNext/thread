package thread_new_demo.deadLock;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 17:56
 **/
public class DeadLock {
    private OtherService otherService;

    private final Object lock = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2(){
        synchronized (lock){
            System.out.println("m2");
            otherService.s1();
        }
    }

}
