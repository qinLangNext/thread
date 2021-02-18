package thread_new_demo.classloader;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-07 16:31
 **/

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类主动使用方式
 */
public class ClassActiveUse {
    public static void main(String[] args) throws ClassNotFoundException {
        //1. 调用new方式
        // new Obj();
        //2. 访问某个类或者接口的静态变量，或者对静态变量进行赋值
        // System.out.println(Obj.salary);
        // System.out.println(I.a);
        //3. 调用静态方法
        // Obj.printSalary();
        //4. 反射类
        // Class.forName("thread_new_demo.classloader.Obj");
        //5. 调用子类时初始化
        // new Child();
        // 5.1 但是子类调用父类的静态变量时，父类初始化，子类不初始化
        // System.out.println(Child.salary);
        //5.2 引用数组的方式也不会初始化
        // Obj[] objs = new Obj[10];
        //5.3 final修饰的常量 编译时被放到常量池中，不会初始化类
        // System.out.println(Obj.salary2);
        //5.4 final修饰的复杂类型，在编译期间无法计算得出，会初始化
        // System.out.println(Obj.x);


    }

}

class Obj{
    public static long salary = 100_000;
    public static final long salary2 = 100_000;
    public static final Random random = new Random();
    //new Random().nextInt(1000)是需要在运行期算出来
    public static final int x = new Random().nextInt(1000);
    static {
        System.out.println("obj 被初始化");
    }

    public static void printSalary(){
        System.out.println("======salary");
    }
}

class Child extends Obj{

    static {
        System.out.println("Child 被初始化");
    }
}

interface I{
    int a=10;

}