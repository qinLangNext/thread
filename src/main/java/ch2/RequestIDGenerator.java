package ch2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-06-18 20:06
 **/
public class RequestIDGenerator implements CircularSeqGenerator {
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = -1;

    public RequestIDGenerator() {
    }

    @Override
    public synchronized short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timeStamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");
        short no = nextSequence();
        return "0094" + timeStamp + df.format(no);
    }

    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }
}
