package thread_new_demo.design_pattern.single;

public class SingleObject4 {

    private SingleObject4(){

    }


    private static class InstanceHolder{
        private static final SingleObject4 instance = new SingleObject4();
    }

    public static SingleObject4 getInstance(){
        return InstanceHolder.instance;
    }
}
