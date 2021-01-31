package thread_new_demo.design_pattern.single;

public class SingleObject1 {
    private static final SingleObject1 instance = new SingleObject1();

    private SingleObject1(){
        //empty
    }

    //can't lazy load
    public SingleObject1 getInstance(){
        return SingleObject1.instance;
    }
}
