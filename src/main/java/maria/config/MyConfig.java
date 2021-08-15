package maria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import maria.services.ProductManagementService;
import maria.services.ShoppingService;
import maria.stores.Store;

@Configuration
public class MyConfig {

    @Bean
    public Store store(){
        return new Store(10, "Maria's");
    }

    @Bean
    public ProductManagementService productManagementService(Store store){
        return new ProductManagementService(store);
    }

    @Bean
    public ShoppingService shoppingService(Store store){
        return new ShoppingService(store);
    }

    @Bean
//    public MessageSource messageSource(){
//        return new StaticMessageSource();
//    }
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("text");
        source.setDefaultEncoding("UTF-8");

        return source;
    }
}
