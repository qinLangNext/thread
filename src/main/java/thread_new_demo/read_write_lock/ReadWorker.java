package thread_new_demo.read_write_lock;

public class ReadWorker extends Thread {

    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] read = data.read();
                System.out.println(2);
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(read));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
