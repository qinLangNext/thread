package semaphore;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-05-15 11:12
 **/
public class Food {

    public String name;

    private long warmTime;

    public Food(String name, long warmTime) {
        this.name = name;
        this.warmTime = warmTime;
    }

    public String getName() {
        return name;
    }

    public long getWarmTime() {
        return warmTime;
    }
}




