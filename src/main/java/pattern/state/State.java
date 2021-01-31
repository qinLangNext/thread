package pattern.state;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 19:52
 **/
public enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
