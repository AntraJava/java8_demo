package functional;

import basic.DoSomethingInterface;
import org.junit.Test;

public class FunctionalLambdaTest {

    @Test
    public void testDosomething() {
        DoSomethingInterface do1 = (i) -> "this is the input : " + i;
//      DoSomethingInterface do1 = (i) -> {return "this is the input : " + i;};
//      DoSomethingInterface do2 = new DoSomethingInterface() {
//            @Override
//            public String doSomething(String param) {
//                return "this is the input: " + param;
//            }
//        };
        letsCall(do1);
    }

    static void letsCall(DoSomethingInterface doIt){
        System.out.println(doIt.doSomething("haha"));
    }


}
