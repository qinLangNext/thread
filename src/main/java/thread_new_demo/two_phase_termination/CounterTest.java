package thread_new_demo.two_phase_termination;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 16:47
 **/
public class CounterTest {
    public static void main(String[] args) {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counterIncrement.close();
    }
}
