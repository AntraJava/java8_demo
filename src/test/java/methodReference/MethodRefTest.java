package methodReference;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MethodRefTest {
    @Test
    public void aTest() {
        IntStream.of(1,2,3,4,5,6,7,8,9,10)
                .filter(NumberOperator::isOdd).forEach(i->System.out.println(i));


        IntStream.of(1,2,3,4,5,6,7,8,9,10)
                .filter(new NumberOperator()::isEven).forEach(System.out::println);
    }
}
