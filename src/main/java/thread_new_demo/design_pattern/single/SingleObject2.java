package thread_new_demo.design_pattern.single;

public class SingleObject2 {
    private static  SingleObject2 instance;

    private SingleObject2(){
        //empty
    }

    //can lazy load
    public synchronized SingleObject2 getInstance(){
        if(instance == null){
            instance = new SingleObject2();
        }
        return SingleObject2.instance;
    }
}
