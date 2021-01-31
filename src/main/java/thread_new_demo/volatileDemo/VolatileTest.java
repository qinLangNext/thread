package thread_new_demo.volatileDemo;

public class VolatileTest {
    private volatile static int INIT_VALUE = 0;
    private final static int MAX_VALUE = 5;

    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value update to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                System.out.printf("update the value to %d\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();

    }
}
