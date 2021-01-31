package thread_new_demo.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 线程池，扩大线程次和减少线程池
 */
public class SimpleThreadPool2 extends Thread {
    private int size;
    private final int queueSize;
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static volatile int seq = 0;
    private final DiscardPolicy discardPolicy;

    private volatile boolean destory = false;

    private int min;
    private int active;
    private int max;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    private final static List<WorkThread> THREAD_QUEUE = new ArrayList<>();

    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardPolicyException("Discard this task.");
    };

    public SimpleThreadPool2() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool2(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        this.size = min;
        for (int i = 0; i < this.size; i++) {
            createWorkTask();
        }
        this.start();
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
    }

    public boolean isDestory() {
        return this.destory;
    }

    @Override
    public void run() {
        while (!destory) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d, QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    size = active;
                    System.out.println("The pool incremented to active.");
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    size = max;
                    System.out.println("The pool incremented to max.");
                }
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {

                        int releaseSize = size - active;
                        for (Iterator<WorkThread> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkThread task = it.next();
                            task.interrupt();
                            task.close();
                            it.remove();
                            --releaseSize;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(Runnable runnable) {
        if (destory) {
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
        synchronized (THREAD_QUEUE) {
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
        }
        this.destory = true;
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
        SimpleThreadPool2 threadPool = new SimpleThreadPool2();
        //SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thre runnable serviced by" + Thread.currentThread() + "finished.");
            });
        }

        Thread.sleep(10_000);

        threadPool.shutdown();
        //
        //threadPool.submit(() -> System.out.println("======"));
    }
}
