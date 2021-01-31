package pattern.chain;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 15:48
 **/
public class HandlerA extends Handler {

    @Override
    public void handle() {
        boolean handled = false;
        if (!handled && successor != null) {
            System.out.println("a");
            successor.handle();
        }
    }
}
