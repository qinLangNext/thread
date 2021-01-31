package thread_new_demo.design_pattern.single;

import java.util.stream.IntStream;

public class SingleObject5 {
    private SingleObject5() {
        //empty
    }

    private enum Single {
        INSTANCE;
        private final SingleObject5 instance;

        Single() {
            instance = new SingleObject5();
        }

        public SingleObject5 getInstance() {
            return instance;
        }
    }

    public static SingleObject5 getInstance() {
        return Single.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100)
                .forEach((i) -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(SingleObject5.getInstance());
                    }
                }.start());
    }
}
