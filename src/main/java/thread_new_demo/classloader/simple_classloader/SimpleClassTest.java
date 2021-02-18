package thread_new_demo.classloader.simple_classloader;

/**
 * 打破双委托机制
 */
public class SimpleClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("thread_new_demo.classloader.simple_classloader.SimpleObject");
        System.out.println(aClass.getClassLoader());
    }
}
