package thread_new_demo.future;

/**
 *
 * Future -> 代表未来的一个结果
 * FutureTask -> 将调用的逻辑进行封装隔离
 * FutureService -> 桥接Future和FutureTask
 **/
public class AsyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        // Future<String> submit = futureService.submit(() -> {
        //     try {
        //         Thread.sleep(10000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     return "finish";
        // });
        futureService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        }, System.out::println);
        System.out.println("============");
        System.out.println("do others");
        Thread.sleep(1000);
        System.out.println("=======");
        // System.out.println(submit.get());
    }
}
