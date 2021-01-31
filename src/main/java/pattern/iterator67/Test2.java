package pattern.iterator67;

import java.util.Iterator;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-29 13:57
 **/
public class Test2 {



    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("1", "1"));
        list.add(new User("2", "2"));
        list.add(new User("3", "3"));
        Iterator iter1 = list.iterator();
        //snapshot: 3, 8, 2
        list.remove(0);//list：3, 8
        Iterator iter2 = list.iterator();//snapshot: 3, 8
        list.remove(1);//list：8
        Iterator iter3 = list.iterator();//snapshot: 3// 输出结果：3 8 2
        list.get(0).setId("5");
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();// 输出结果：3 8
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }
        System.out.println();// 输出结果：8
        while (iter3.hasNext()) {
            System.out.print(iter3.next() + " ");
        }
        System.out.println();
    }
}
