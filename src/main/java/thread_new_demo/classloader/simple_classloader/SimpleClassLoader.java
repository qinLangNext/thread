package thread_new_demo.classloader.simple_classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 打破双委托机制
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-08 10:47
 **/
public class SimpleClassLoader extends ClassLoader {
    private final static String DEFAULT_DIR = "E:\\mnt";
    private String dir = DEFAULT_DIR;
    private String classLoaderName;

    public SimpleClassLoader() {
        super();
    }

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public SimpleClassLoader(String classLoaderName, ClassLoader classLoader) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        if (name.startsWith("java.")){
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                clazz = systemClassLoader.loadClass(name);
                if (clazz != null){
                    resolveClass(clazz);
                }
                return clazz;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            clazz = findClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (clazz == null && getParent() != null){
            getParent().loadClass(name);
        }
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under " + dir);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class" + name + "failed.");
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

}
