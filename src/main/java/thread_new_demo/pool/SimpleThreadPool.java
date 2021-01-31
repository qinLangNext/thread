package thread_new_demo.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {
    private final int size;
    private final int queueSize;
    private final static int DEFAULT_SIZE = 10;
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static volatile int seq = 0;
    private final DiscardPolicy discardPolicy;

    private volatile boolean distory = false;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    private final static List<WorkThread> THREAD_QUEUE = new ArrayList<>();

    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardPolicyException("Discard this task.");
    };

    public SimpleThreadPool() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDistory() {
        return this.distory;
    }

    public void submit(Runnable runnable) {
        if (distory) {
            throw new IllegalStateException("The thread pool already destroy and not allow submit task. ");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.add(runnable);
            TASK_QUEUE.notifyAll();
        }

    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int initValue = THREAD_QUEUE.size();
        while (initValue > 0) {
            for (WorkThread task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) {
                    task.interrupt();
                    task.close();
                    --initValue;
                } else {
                    //该处休息是为了让其他任务线程抢到资源
                    Thread.sleep(10);
                }
            }
        }
        this.distory = true;
        System.out.println("The thread pool disposed.");
    }

    private void createWorkTask() {
        WorkThread workThread = new WorkThread(GROUP, THREAD_PREFIX + (seq++));
        workThread.start();
        THREAD_QUEUE.add(workThread);
    }


    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD;
    }

    public static class DiscardPolicyException extends RuntimeException {
        public DiscardPolicyException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardPolicyException;
    }

    private static class WorkThread extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUTER:
            while (taskState != TaskState.DEAD) {
                //System.out.println("===");
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            //System.out.println("outer" + e);
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        //SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thre runnable serviced by" + Thread.currentThread() + "finished.");
            });
        }

        Thread.sleep(10_000);

        threadPool.shutdown();

        threadPool.submit(()-> System.out.println("======"));
    }
}
