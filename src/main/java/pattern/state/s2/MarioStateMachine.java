package pattern.state.s2;

import pattern.state.State;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-28 10:58
 **/
public class MarioStateMachine {
    private int score;
    private IMario mario;

    public MarioStateMachine() {
        this.score = 0;
        this.mario = new SmallMario(this);
    }

    public void obtainMushroom() {
        mario.obtainMushRoom();
    }

    public void obtainCpae() {
        mario.obtainCape();
    }

    public void obtainFireFlower() {
        mario.obtainFireFlower();
    }

    public State getCurrentState() {
        return this.mario.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setCurrentMario(IMario mario) {
        this.mario = mario;
    }
}
