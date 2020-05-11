package quiz;

import java.util.ArrayList;
import java.util.List;

public class Questions {

    static List<Customer> customerList = new ArrayList<>();

    static {
        customerList.add(new Customer(1,"James", 25));
        customerList.add(new Customer(2,"Ada", 23));
        customerList.add(new Customer(3,"Grace", 35));
        customerList.add(new Customer(4,"Bob", 28));
        customerList.add(new Customer(5,"Cindy", 20));
        customerList.add(new Customer(6,"Ryan", 40));
        customerList.add(new Customer(7,"Alan", 44));
    }

    // Get a list of Customer who age is greater than 30.
    // Result list should be in descending order of Customer's Name.
    static class Q1 {
        public static void main(String[] args) {
            List<Customer> customerList = null; // write your code here;
            System.out.println(customerList);
        }
    }

    // Get indexes of Customer whose name starts with "A" or "a".
    static class Q2 {
        public static void main(String[] args) {
            List<Integer> indexes = null; //write your code here;
            System.out.println(indexes);
        }
    }

}

class Customer {
    private int id;
    private String name;
    private int age;

    public Customer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}