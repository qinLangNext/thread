package semaphore;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-05-15 11:14
 **/
public class MicrowaveOvenPool {

    private List<MicrowaveOven> microwaveOvens;

    private Semaphore semaphore;

    public MicrowaveOvenPool(int size,@NotNull List<MicrowaveOven> microwaveOvens) {
        this.microwaveOvens = new Vector<>(microwaveOvens);
        this.semaphore = new Semaphore(size);
    }
    public Food exec(Function<MicrowaveOven, Food> func) {
        MicrowaveOven microwaveOven = null;
        try{
            semaphore.acquire();
            microwaveOven = microwaveOvens.remove(0);
            return func.apply(microwaveOven);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            microwaveOvens.add(microwaveOven);
            semaphore.release();
        }
        return null;
    }

}