package pattern.state.s2;

import pattern.state.State;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 11:24
 **/
public class SuperMario implements IMario {
    private MarioStateMachine marioStateMachine;

    public SuperMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {

    }

    @Override
    public void obtainCape() {

    }

    @Override
    public void obtainFireFlower() {

    }

    @Override
    public void meetMonster() {

    }
}
