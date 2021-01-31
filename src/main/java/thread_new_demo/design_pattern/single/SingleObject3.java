package thread_new_demo.design_pattern.single;

public class SingleObject3 {
    private static volatile SingleObject3 instance;

    private SingleObject3(){
        //empty
    }

    //can lazy load, double check
    public SingleObject3 getInstance(){
        if(instance == null){
            synchronized (SingleObject3.class){
                //该处判断是为null，可能当时有多个线程争抢该锁，某一个线程instance后，instance不为null
                //的时候另外线程获得锁还需要判断是否为null
               if (instance == null){
                   instance = new SingleObject3();
               }
            }
        }
        return SingleObject3.instance;
    }
}
