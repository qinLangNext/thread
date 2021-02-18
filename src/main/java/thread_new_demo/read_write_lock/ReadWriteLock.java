package thread_new_demo.read_write_lock;

public class ReadWriteLock {
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException {
        waitingReaders++;
        try {
            while (this.writingWriters > 0 || (preferWriter && waitingWriters > 0)){
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
        System.out.println("r"+Thread.currentThread().getName());
    }

    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void  writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders>0 || writingWriters >0){
                this.wait();
            }
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock(){
        this.writingWriters--;
        this.notifyAll();
    }
}
