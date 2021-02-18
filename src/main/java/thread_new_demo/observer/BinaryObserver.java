package thread_new_demo.observer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 14:37
 **/
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        super(subject);
    }
    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(this.subject.getState()));
    }
}
