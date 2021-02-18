package thread_new_demo.threadLocal;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 17:59
 **/
public class QueryFromDBAction {
    public void execute() {
        try {
            Thread.sleep(1000);
            String name = "ql " + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
