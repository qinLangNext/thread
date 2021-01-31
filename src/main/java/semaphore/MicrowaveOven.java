package semaphore;

import semaphore.Food;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-05-15 11:13
 **/
public class MicrowaveOven {

    public String name;

    public MicrowaveOven(String name) {
        this.name = name;
    }

    public Food warm(Food food) {
        long second = food.getWarmTime() * 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("%s warm %s %d seconds food.", name,food.getName() ,food.getWarmTime()));
        return food;
    }

    public String getName() {
        return name;
    }
}
