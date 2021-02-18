package thread_new_demo.threadLocal;

import java.util.stream.IntStream;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 18:36
 **/
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(i-> {
                    new Thread(new ExecutionTask()).start();
                });
    }
}
