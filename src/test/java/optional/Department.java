package optional;

import java.util.Optional;

/**
 * Created by daweizhuang on 5/12/17.
 */
public class Department {

    private Optional<Product> product;

    public Department() {
    }

    public Department(Optional<Product> product) {
        this.product = product;
    }

    public Optional<Product> getProduct() {
        return product;
    }

    public void setProduct(Optional<Product> product) {
        this.product = product;
    }
}
