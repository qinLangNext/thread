package pattern.chain;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 15:47
 **/
public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    public abstract void handle();
}
