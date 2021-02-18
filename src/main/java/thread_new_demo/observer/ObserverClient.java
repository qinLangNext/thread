package thread_new_demo.observer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 14:44
 **/
public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        subject.setState(1);
        subject.setState(2);
        subject.setState(10);
    }
}
