import exceptions.*;
import products.*;
import services.ProductManagementService;
import services.ShoppingService;
import stores.Store;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Book b1 = new Book("b1", 12, 7);
        Soap s1 = new Soap("s1", 7, 6);
        Cake c1 = new Cake("c1", 9, 8, 7);
        Cake c2 = new Cake("c2", 4, 4, 2);
        Cake c3 = new Cake("c3", 16, 2, 5);


        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Store store = context.getBean(Store.class);
        ProductManagementService productManager = context.getBean(ProductManagementService.class);
        ShoppingService shoppingService = context.getBean(ShoppingService.class);

        try {
            productManager.addProduct(b1);
            productManager.addProduct(s1);
            productManager.addProduct(c1);
            productManager.addProduct(c2);
            productManager.addProduct(c3);
        } catch (StoreFullException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(
                context.getMessage(
                        "storeSet",
                        new Object[0],
                        Locale.forLanguageTag("ru-RU")
                ) + store.getProductSet()
        );

        shoppingService.buy(c1);
        System.out.println(
                context.getMessage(
                        "storeSet",
                        new Object[0],
                        Locale.forLanguageTag("ru-RU")
                ) + store.getProductSet()
        );
    }
}