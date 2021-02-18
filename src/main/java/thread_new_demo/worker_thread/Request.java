package thread_new_demo.worker_thread;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 13:42
 **/
public class Request {
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed." + this);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
