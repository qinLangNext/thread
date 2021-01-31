package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-07-13 19:28
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(50, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        long aa = System.nanoTime();
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> getA(), executor);
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> getB(), executor);
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> getC(), executor);
        // CompletableFuture<Void> all = CompletableFuture.allOf(a, b, c);
        System.out.println(getE());
         CompletableFuture.allOf(a, b, c).join();
        long bb = System.nanoTime();
        System.out.println((bb-aa)/1000);

        // all.join();
        System.out.println(a.get());
        System.out.println(b.get());
        System.out.println(c.get());
        long cc = System.nanoTime();
        System.out.println((cc-aa)/1000);
    }

    private static String getA(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "a";
    }
    private static String getB(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "b";
    }
    private static String getC(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "c";
    }

    private static String getD(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "d";
    }

    private static String getE(){
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "e";
    }
}
