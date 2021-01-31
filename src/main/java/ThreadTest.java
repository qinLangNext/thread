/**
 * @program: videoAd
 * @author: lang.qin
 * @create: 2020-05-11 20:30
 **/
public class ThreadTest {
    public static void main(String[] args) {
        int i = 2 * 5 / 100;

        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            System.out.println("a");
            try {
                Thread.sleep(100);
                System.out.println("r");
            }catch (InterruptedException e){
                System.out.println("b");
            Thread.currentThread().interrupt();
                System.out.println("c");
            e.printStackTrace();
        }
    }
    }
}
