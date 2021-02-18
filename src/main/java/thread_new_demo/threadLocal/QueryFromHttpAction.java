package thread_new_demo.threadLocal;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 18:01
 **/
public class QueryFromHttpAction {
    public void execute() {

        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "4123423" + Thread.currentThread().getId();
    }
}
