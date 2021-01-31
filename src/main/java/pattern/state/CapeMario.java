package pattern.state;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-27 20:14
 **/
public class CapeMario implements IMario {
    private MarioStateMachine marioStateMachine;

    public CapeMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.CAPE;
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
        marioStateMachine.setCurrentState(new SmallMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() - 300);
    }
}
