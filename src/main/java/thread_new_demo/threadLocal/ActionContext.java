package thread_new_demo.threadLocal;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 17:53
 **/
public class ActionContext {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    /**
     * 单例模式
     */
    private static class ContextHolder{
        private static ActionContext context = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.context;
    }

    public  Context getContext(){
        return threadLocal.get();
    }
}
