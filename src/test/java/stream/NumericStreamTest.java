package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumericStreamTest {
    @Test
    public void testIntStream() {
//        String result = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map(i -> i * 2).mapToObj(String::valueOf).collect(Collectors.joining(", "));
//        System.out.println(result);
        int[] a = {5, 1, 2, 3, 45};
//        Arrays.stream(a).forEach(System.out::println);
//        IntStream.range(0,5).forEach(i->System.out.println(a[a.length-i-1]));
        Comparator<Integer> comparator = (i,j) -> -1;
        Arrays.stream(a).boxed().sorted(comparator).forEach(System.out::println);
    }


}
