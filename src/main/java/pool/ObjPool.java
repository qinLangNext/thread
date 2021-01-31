package pool;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-05-15 11:29
 **/
class ObjPool<T, R> {
    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;
    // 构造函数
    ObjPool(int size, List<T> t){
        pool = new Vector<T>(){};
        // List<T> list = (List<T>) t;
        for(int i=0; i<t.size(); i++){
            pool.add(t.get(i));
        }
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用func
    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }
}


