package pool;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-05-15 11:30
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i=0; i<10; i++){
            list.add("msg+"+i);
        }
        // 创建对象池
        ObjPool<String, String> pool =
                new ObjPool<>(10, list);

        for (int i=0; i<100; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 通过对象池获取t，之后执行
                    try {
                        pool.exec(t -> {
                            System.out.println("当前线程id:"+Thread.currentThread().getId()+",当前获取到的对象："+t);
                            return t.toString();
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
