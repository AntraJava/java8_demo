package optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by daweizhuang on 5/12/17.
 */
public class Demo {

    Optional<Department> dept ;
    @Before
    public void init(){
        dept = Optional.of(new Department(Optional.empty()));
//        dept = Optional.empty();
    }

    @Test
    public void doTestNull() {
        String name = dept.flatMap(Department::getProduct).map(Product::getName).get();

        System.out.println(name);
    }



}
