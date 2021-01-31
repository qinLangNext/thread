package pattern.iterator66;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 17:48
 **/
public class Test {
    public static void main(String[] args) {
        List names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        Iterator iterator1 = names.iterator();
        Iterator iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        Object next = iterator2.next();// 运行结果？
        System.out.println();
    }
}
