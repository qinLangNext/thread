package pattern.state;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 20:01
 **/
public class Test {
    public static void main(String[] args) {
        MarioStateMachine m = new MarioStateMachine();
        m.obtainMushRoom();
        m.obtainCape();
        m.meetMonster();
        int score = m.getScore();
        int value = m.getCurrentState().getValue();
        System.out.println(score + "=" + value);

    }
}
