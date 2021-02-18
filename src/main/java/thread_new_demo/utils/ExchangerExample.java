package thread_new_demo.utils;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(()->{
            Object o = new Object();
            //received and send 都是同一object，可能存在线程问题
            System.out.println("A will send the object "+ o);
            try {
                Object exchange = exchanger.exchange(o);
                System.out.println("A received the object "+ exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            Object o = new Object();
            System.out.println("B will send the object "+ o);
            try {
                Object exchange = exchanger.exchange(o);
                System.out.println("B received the object "+ exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
