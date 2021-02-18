package thread_new_demo.observer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 14:24
 **/
public abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
