package thread_new_demo.multi_thread_producer_consumer;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-05 10:51
 **/
public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
