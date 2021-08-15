package maria.services;

import maria.products.Product;
import maria.stores.Store;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class ShoppingService implements ApplicationContextAware {

    private Store store;
    private ApplicationContext ctx;

    public ShoppingService(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void buy(Product product){

        System.out.println(ctx.getMessage("eventOccurred", new String[]{product.getName()},Locale.forLanguageTag("ru-RU")));
        ctx.publishEvent( new PurchaseEvent(product));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

}
