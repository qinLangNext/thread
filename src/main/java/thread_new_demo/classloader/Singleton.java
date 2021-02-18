package thread_new_demo.classloader;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 17:45
 **/
public class Singleton {
    private static Singleton singleton = new Singleton();
    private static int x = 0;
    private static int y;



    private Singleton() {
        this.x = 1;
        this.y = 1;
    }

    /**
     * 1. 链接准备阶段：为类的静态变量分配内存，并将其初始化为默认值
     *     private static int x = 0;
     *     private static int y;
     *
     *     private static Singleton singleton = new Singleton();
     *
     *     private Singleton() {
     *         this.x = 1;
     *         this.y = 1;
     *     }
     *  执行顺序
     *  x = 0;
     *  y = 0;
     *  singleton = null;
     *  初始化：为类的静态变量赋予正确的值
     *  singleton = new Singleton();
     *      x= 1;
     *      y=1;
     *  x=0;
     *  y=1;
     *
     */

    /**
     * 2. 链接准备阶段
     *     private static Singleton singleton = new Singleton();
     *     private static int x = 0;
     *     private static int y;
     *
     *     private Singleton() {
     *         this.x = 1;
     *         this.y = 1;
     *     }
     *
     *  执行顺序
     *  singleton = null;
     *  x = 0;
     *  y = 0;
     *
     *  初始化：为类的静态变量赋予正确的值
     *  singleton = new Singleton();
     *  x= 1;
     *  y=1;
     *
     */
    public static Singleton getSingleton(){
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton = getSingleton();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
