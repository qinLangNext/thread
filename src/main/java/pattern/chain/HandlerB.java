package pattern.chain;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 15:48
 **/
public class HandlerB extends Handler {

    @Override
    public void handle() {
        boolean handled = false;
        System.out.println("b");
        if (!handled && successor != null) {
            System.out.println("b1");
            successor.handle();
        }
    }
}
