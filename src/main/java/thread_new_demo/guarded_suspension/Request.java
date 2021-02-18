package thread_new_demo.guarded_suspension;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 15:45
 **/
public class Request {
    private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
