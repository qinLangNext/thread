package thread_new_demo.guarded_suspension;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 16:46
 **/
public class InterruptDemo {
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                System.out.println("1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "t1").start();
        Thread t2 = new Thread(() -> {
            while (true) {
                test();
                // System.out.println("1");
                // try {
                //     Thread.sleep(1000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                //     break;
                // }
            }
        }, "t2");
        t2.start();
        t2.interrupt();

    }

    public static synchronized void test(){
        try {
            InterruptDemo.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
