package pattern.eventbus;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 15:59
 **/
public class TestClient {
    public static void main(String[] args) {
        UserController controller = new UserController();
        List<Object> observers = new ArrayList<>();
        observers.add(new RegPromotionObserver());
        controller.setRegObserver(observers);

        Iterator<Object> iterator = observers.iterator();
        iterator.remove();

        controller.register("1", "2");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
