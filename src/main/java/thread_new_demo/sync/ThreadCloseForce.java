package thread_new_demo.sync;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-01-21 16:51
 **/
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long a = System.currentTimeMillis();
        service.execute(()->{
            // 1. load a heavy resource.
            // while (true){
            //
            // }

            // 2.
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000);
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }
}
