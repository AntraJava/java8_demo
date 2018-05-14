package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumericStreamTest {
    @Test
    public void testIntStream() {
        String result = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map(i -> i * 2).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(result);
        int[] a = {1, 2, 3, 45};
        Arrays.stream(a).forEach(System.out::println);
    }


}
