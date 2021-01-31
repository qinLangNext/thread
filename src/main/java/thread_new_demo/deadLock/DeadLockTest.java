package thread_new_demo.deadLock;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 18:05
 **/
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(){
            @Override
            public void run() {
                while (true){
                    deadLock.m1();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
