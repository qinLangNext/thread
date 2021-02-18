package thread_new_demo.threadLocal;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 18:07
 **/
public class ExecutionTask implements Runnable {
    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();
    private QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();


    @Override
    public void run() {

        queryFromDBAction.execute();
        System.out.println("the name query successful");
        queryFromHttpAction.execute();
        System.out.println("the cardId query successful");
        Context context = ActionContext.getActionContext().getContext();
        System.out.println(context.getName() + " "+ context.getCardId());
    }
}
