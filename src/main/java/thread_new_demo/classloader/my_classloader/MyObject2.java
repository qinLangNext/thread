package thread_new_demo.classloader.my_classloader;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-08 11:25
 **/
public class MyObject2 {
    static {
        System.out.println("MyObject static block.");
    }

    public String hello() {
        return "hello world";
    }
}
