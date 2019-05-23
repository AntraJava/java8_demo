package functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class FunctionalLambdaTest {

    @Test
    public void testDosomething() {
        DoSomethingInterface do1 = (i) -> "this is the input : " + i;

        DoSomethingInterface do11 = (i) -> {
            System.out.println();
            return "this is the input : " + i;
        };

        DoSomethingInterface do2 = new DoSomethingInterface() {
            @Override
            public String doSomething(String param) {
                return "this is the input: " + param;
            }
        };
        letsCall(do1);
    }

    static void letsCall(DoSomethingInterface doIt){
        System.out.println(doIt.doSomething("haha"));
    }


}
