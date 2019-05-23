package stream;

import functional.Employee;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    List<Employee> list = new ArrayList<>();
    Map<Employee, String> map = new TreeMap<>();
    @Before
    public void init() {
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
        Employee yongestEMP = list.stream().min(Comparator.comparing(Employee::getAge).reversed()
                .thenComparing(Employee::getName)).orElseGet(Employee::new);


        Employee yongestEMP2 = list.stream().min((i,j)->{
            int res = i.getAge()-j.getAge();
            if(res == 0){
                res = i.getName().compareTo(j.getName());
                if(res ==0){

                }
            }
            return res;
        }).get();


        Employee yongestEMP1 = list.stream().min(new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                int res = o1.getAge()-o2.getAge();
                if(res == 0){
                    res = o1.getName().compareTo(o2.getName());
                    if(res ==0){

                    }
                }
                return res;
            }
        }).get();
        System.out.println("Yongest Employee is "+yongestEMP);

        Employee empWithMaxSalary = list.stream().max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println("MaxSalary Employee is "+empWithMaxSalary);

    }

    @Test
    public void testSorting(){
//        list.stream().sorted().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////////////");

        list.stream().sorted((o1, o2) -> o2.getAge() - o1.getAge())
            .forEach((i)->{System.out.println(i.getAge());});

        List<Employee> newList = list.stream().sorted().collect(Collectors.toList());

//        System.out.println("/////////////////////////////////////////////////////");
        list.forEach((i)->System.out.println(i));
        list.sort((o1, o2) -> o2.getAge() - o1.getAge());
//
//        System.out.println("/////////////////////////////////////////////////////");
        list.stream().sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed().thenComparing(Employee::getAge))
                .forEach(System.out::println);
    }

    @Test
    public void testFilter(){
       list.stream().filter(i->i.getAge()>30)
                .filter(i->i.getAge()< 100).forEach(i->System.out.println(i + "."));
    }

    @Test
    public void testMap(){
//        int i = list.stream().mapToInt(e->e.getAge()).sum();
//        System.out.println("Total age is "+i);
        Stream s = list.stream().sorted((e,f) -> {
            return f.getAge() -e.getAge();
        });

        s.forEach(System.out::println);

        System.out.println("///////////////////////");


        list.stream().forEach(i->{
            System.out.println(i);
        });


        List<String> name = list.stream()
                .map(e -> e.getName() + " " + e.getAge()).collect(Collectors.toList());



        System.out.println(name.stream().collect(Collectors.joining(" * ")));
//        System.out.println(name);
    }

    @Test
    public void testGroupBy(){

        Map<Boolean, List<Employee>> aMap = list.stream().collect(Collectors.partitioningBy(o -> o.getSalary()>1000));
        aMap.forEach((k,v) ->System.out.println(k + " " + v));

        Map<String, List<Employee>> bMap = list.stream()
                .collect(Collectors.groupingBy(o -> o.getSalary()>=1000?"Greater than/equal to 1000":"Less than 1000"));


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
    public void testFlatMap(){

        Map<String, List<Employee>> bMap = list.stream().collect(Collectors.groupingBy(o -> o.getSalary()>=1000?"Greater than/equal to 1000":"Less than 1000"));

        List<Employee> listOfEmp = bMap.values().stream().flatMap(employees -> {
            System.out.println(12345);return employees.stream();}).collect(Collectors.toList());

        listOfEmp.forEach(System.out::println);
    }

    @Test
    public void testChange(){
        list.stream().filter(e->{
            return e.getAge()>100;
        }).peek(e->{
            e.setName("sdf");
            }).forEach(e->e.setAge(e.getAge()+1));
        list.forEach(System.out::println);
    }


    @Test
    public void testMatch(){
        boolean allSalary = list.stream().allMatch(e -> e.getSalary() > 100);
        System.out.println(allSalary);

        boolean any = list.stream().anyMatch(e -> e.getSalary() > 100);
        System.out.println(any);

        boolean none = list.stream().noneMatch(e -> e.getSalary() > 100);
        System.out.println(none);
    }

    @Test
    public void testPeek(){
        list.stream().peek(e->e.setAge(e.getAge()+10)).filter(e->e.getAge()>50).peek(e->e.setAge(10)).forEach(System.out::println);
    }

    @Test
    public void testDistinctCount(){
        long count = list.stream().distinct().count();
        System.out.println(count);
    }

    @Test
    public void testSkipLimit(){
        list.stream().skip(2).limit(2).forEach(System.out::println);
    }

    @Test
    public void testReduce(){
        Employee emp = list.stream().reduce((employee, employee2)->{
            employee.setSalary(employee2.getSalary()+employee.getSalary());
            employee.setAge(employee2.getAge()+employee.getAge());
            employee.setName(employee.getName()+", "+employee2.getName());
            return employee;
        }).get();
        System.out.println(emp);
    }

    @Test
    public void test(){
        LinkedList<Employee> newList = list.stream().collect(LinkedList::new, (list, employee) -> {list.add(employee);}, (list, objects2) -> {list.addAll(objects2);});
    }
}
