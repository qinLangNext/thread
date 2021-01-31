package pattern.iterator67;

import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-29 14:15
 **/
public class Test3 {
    public static void main(String[] args) {
        List<Integer> list = new java.util.ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> list2 = new java.util.ArrayList<>(list);
        list.remove(1);
        System.out.println();
    }
}
