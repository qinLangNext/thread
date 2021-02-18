package thread_new_demo.classloader.my_classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-08 11:29
 **/
public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myclassloader = new MyClassLoader("myclassloader");
        Class<?> clazz = myclassloader.loadClass("thread_new_demo.classloader.my_classloader.MyObject");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        Object o = clazz.newInstance();
        Method method = clazz.getMethod("hello", new Class<?>[]{});
        Object invoke = method.invoke(o, new Object[]{});
        System.out.println(invoke);
    }
}
