package pattern.state;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 19:49
 **/
public interface IMario {
    State getName();

    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}
