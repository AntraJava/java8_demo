package stream;

import basic.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {

    List<Employee> list = new ArrayList<>();

    @Before
    public void init(){
        Employee emp1 = new Employee(20, "Name1", 800);
        Employee emp2 = new Employee(30, "Name2", 2000);
        Employee emp3 = new Employee(40, "Name3", 3000);
        Employee emp4 = new Employee(50, "Name4", 3000);
        Employee emp5 = new Employee(50, "Name5", 5000);

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        list.add(emp4);
        list.add(emp5);
    }

    @Test
    public void testMinMax(){
        Employee yongestEMP = list.stream().min(Comparator.comparing(Employee::getAge)).get();
        System.out.println("Yongest Employee is "+yongestEMP);

        Employee empWithMaxSalary = list.stream().max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println("MaxSalary Employee is "+empWithMaxSalary);

    }

    @Test
    public void testGroupBy(){

        Map<Boolean, List<Employee>> aMap = list.stream().collect(Collectors.partitioningBy(o -> o.getSalary()>1000));
        aMap.forEach((k,v) ->System.out.println(k + " " + v));

        Map<String, List<Employee>> bMap = list.stream().collect(Collectors.groupingBy(o -> o.getSalary()>=1000?"Greater than/equal to 1000":"Less than 1000"));
        bMap.forEach((k,v) ->System.out.println(k + " " + v));

        Map<String, List<Employee>> cMap = list.stream().collect(Collectors.groupingBy(o->{
            if(o.getSalary() > 100 && o.getSalary() < 1000){
                return "Not a big deal";
            }else if(o.getSalary() >= 1000 && o.getSalary() < 5000){
                return "Is a big deal";
            }else{
                return "Real Man";
            }
        }));
        cMap.forEach((k,v) ->System.out.println(k + " " + v));
    }

    @Test
    public void testSorting(){
//        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted(((o1, o2) -> {return o1.getAge() - o2.getAge();})).forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////////////");
        list.stream().sorted(((o1, o2) -> o2.getAge() - o1.getAge())).forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////////////");
        list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getAge)).forEach(System.out::println);
    }

    @Test
    public void testFilter(){
        list.stream().filter(i->i.getAge()>30).forEach(i->System.out.println(i));
    }
}
