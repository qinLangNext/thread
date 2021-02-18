package thread_new_demo.threadLocal;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2021-02-04 17:52
 **/
public class Context {
    private String name;
    private String cardId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
