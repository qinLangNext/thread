package thread_new_demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-03 14:26
 **/
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }
    public void setState(int state){
        if(this.state != state){
            this.state = state;
            notifyAllObserver();
        }
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }
}
