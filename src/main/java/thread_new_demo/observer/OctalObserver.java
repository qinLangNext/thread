package thread_new_demo.observer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 14:43
 **/
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:"+Integer.toOctalString(this.subject.getState()));
    }
}
