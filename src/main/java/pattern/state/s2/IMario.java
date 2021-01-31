package pattern.state.s2;

import pattern.state.State;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 10:51
 **/
public interface IMario {
    State getName();

    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}
