package maria;

import maria.exceptions.IncorrectInputException;
import maria.exceptions.StoreFullException;
import maria.config.MyConfig;
import maria.products.Book;
import maria.products.Cake;
import maria.products.Soap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import maria.services.ProductManagementService;
import maria.services.ShoppingService;
import maria.stores.Store;
import java.util.Locale;

@SpringBootApplication
@Import(MyConfig.class)
@ComponentScan({"maria.stores"})
public class Main {
    public static void main(String[] args) {

        Book b1 = new Book("b1", 12, 7);
        Soap s1 = new Soap("s1", 7, 6);
        Cake c1 = new Cake("c1", 9, 8, 7);
        Cake c2 = new Cake("c2", 4, 4, 2);
        Cake c3 = new Cake("c3", 16, 2, 5);


        final ConfigurableApplicationContext context = SpringApplication.run(Main.class);
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

        //метод который использует связь между Store и ProductSearchIndex для проверки @Autowired
        try {
            System.out.println(store.productsLike("*1"));
        } catch (IncorrectInputException e){
            System.out.println(e.getMessage());
        }
    }
}