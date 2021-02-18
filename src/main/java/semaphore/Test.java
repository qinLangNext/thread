//package semaphore;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//
///**
// * @program: thread
// * @author: lang.qin
// * @create: 2020-05-15 11:20
// **/
//public class Test {
//    public static void main(String[] args) {
//        MicrowaveOven a = new MicrowaveOven("a");
//        MicrowaveOven b = new MicrowaveOven("b");
//        List<MicrowaveOven> microwaveOvens = new ArrayList<MicrowaveOven>(){{
//            add(a);
//            add(b);
//        }};
//        MicrowaveOvenPool microwaveOvenPool = new MicrowaveOvenPool(10, microwaveOvens);
//        Food aa = microwaveOvenPool.exec(new Function<MicrowaveOven, Food>() {
//            @Override
//            public Food apply(MicrowaveOven microwaveOven) {
//                Food aa = microwaveOven.warm(new Food("aa", 1L));
//                return aa;
//            }
//        });
//         microwaveOvenPool.exec(new Function<MicrowaveOven, Food>() {
//            @Override
//            public Food apply(MicrowaveOven microwaveOven) {
//                Food aa = microwaveOven.warm(new Food("bb", 1L));
//                return aa;
//            }
//        });
//        microwaveOvenPool.exec(new Function<MicrowaveOven, Food>() {
//            @Override
//            public Food apply(MicrowaveOven microwaveOven) {
//                Food aa = microwaveOven.warm(new Food("cc", 1L));
//                return aa;
//            }
//        });
//    }
//}
