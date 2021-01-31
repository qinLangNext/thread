package pattern.chain;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 15:54
 **/
public class Application {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}
