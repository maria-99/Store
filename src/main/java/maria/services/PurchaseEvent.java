package maria.services;

import maria.products.Product;
import org.springframework.context.ApplicationEvent;

public class PurchaseEvent extends ApplicationEvent {

    private Product boughtProduct;

    public PurchaseEvent(Product source) {
        super(source);
        this.boughtProduct = source;
    }


    public Product getBoughtProduct() {
        return boughtProduct;
    }
}
