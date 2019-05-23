package stream;

import functional.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ParalleStream {

    List<Employee> bigList = new ArrayList<>();

    @Before
    public void init(){
        long start = System.nanoTime();
        for(int i = 0 ; i < 5_000; i ++) {
            bigList.add(getRandomEmployee());
        }
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time used in msecs to build data: "+duration);
    }

    private Employee getRandomEmployee() {
        Random r1 = new Random();
        int age = r1.nextInt(90);
        int salary = r1.nextInt(10000);
        byte[] name = new byte[5];
        name[0] = (byte) (r1.nextInt(25) +65) ;
        name[1] = (byte) (r1.nextInt(25) +65) ;
        name[2] = (byte) (r1.nextInt(25) +65) ;
        name[3] = (byte) (r1.nextInt(25) +65) ;
        name[4] = (byte) (r1.nextInt(25) +65) ;

        String s = new String(name);
        return new Employee(age,s,salary);
    }

    @Test
    public void test1() {
        long start = System.nanoTime();
        bigList.stream().filter(e->e.getNameSlowly().contains("A")).mapToInt(e->e.getAge()).average().getAsDouble();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time used in msecs to process singleThread: "+duration);

        long start1 = System.nanoTime();
        bigList.parallelStream().filter(e->e.getNameSlowly().contains("A")).mapToInt(e->e.getAge()).average().getAsDouble();
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Time used in msecs to process palallelly: "+duration1);
    }

    @Test
    public void test2() {
        long start = System.nanoTime();
        bigList.stream().max(Comparator.comparing(Employee::getNameSlowly)).get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time used in msecs to process singleThread: "+duration);

        long start1 = System.nanoTime();
        bigList.parallelStream().max(Comparator.comparing(Employee::getNameSlowly)).get();
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Time used in msecs to process palallelly: "+duration1);
    }

    @Test
    public void testReduce(){
        long start = System.nanoTime();
        Employee emp = bigList.parallelStream().reduce((employee, employee2)->{
                employee.setSalary(employee2.getSalary()+employee.getSalary());
                employee.setAge(employee2.getAge()+employee.getAge());
                employee.setName(employee.getName()+", "+employee2.getName());
                return employee;
        }).get();
        System.out.println(emp);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time used in msecs to process singleThread: "+duration);

    }
}
