package thread_new_demo.read_write_lock;

public class SharedData {
    private final ReadWriteLock LOCK = new ReadWriteLock(false);
    private final char[] buffer;

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            LOCK.readLock();
            return doRead();
        } finally {
            LOCK.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            LOCK.writeLock();
            this.doWrite(c);
        }finally {
            LOCK.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i=0; i<buffer.length; i++){
            buffer[i] = c;
        }
        slow(10);
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i] = buffer[i];
        }
        System.out.println("r");
        slow(50);
        return newBuf;
    }

    private void slow(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
