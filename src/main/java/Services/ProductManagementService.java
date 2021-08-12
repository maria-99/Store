package Services;

import Exceptions.NoSuchProductException;
import Exceptions.StoreFullException;
import Products.Product;
import Stores.ProductSearchIndex;
import Stores.Store;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;

public class ProductManagementService implements ApplicationListener<PurchaseEvent>, ApplicationContextAware {

    private Store store;
    private Set<Product> productSet;
    private int maxSize;
    private ProductSearchIndex productSI;
    private ApplicationContext ctx;

    public ProductManagementService(Store store) {
        this.store = store;
        productSet = store.getProductSet();
        maxSize = store.getMaxSize();
        productSI = store.getProductSI();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    //добавляет товар в список товаров магазина (если размер !> заданого макса)
    public void addProduct(Product product) throws StoreFullException {
        if (productSet.size() < maxSize) {
            productSet.add(product);
            store.getProductSI().addProduct(product);
        } else {
            throw new StoreFullException(maxSize);
        }
    }

    //удаляет товары с наличием в названии указанного в параметре текста
    public void deleteProductsLike(String sample) throws NoSuchProductException {
        boolean deleted = productSet.removeIf(n -> {
            boolean b = n.getName().toLowerCase().contains(sample.toLowerCase());
            if (b) {
                productSI.deleteProduct(n);
            }
            return b;
        });
        if (!deleted) {
            throw new NoSuchProductException(sample);
        }
    }

    //возможность удалять товары по предикату товара
    public void deleteIf(Predicate<Product> predicate) {
        productSet.removeIf(n -> {
            boolean b = predicate.test(n);
            if (b){
                productSI.deleteProduct(n);
            }
            return b;
        });
    }

    //TODO add exception
    public void deleteProduct(Product product){
        productSet.remove(product);
    }

    @Override
    public void onApplicationEvent(PurchaseEvent purchaseEvent) {
        System.out.println(
                ctx.getMessage(
                        "eventHandling",
                        new String[]{purchaseEvent.getBoughtProduct().getName()},
                        Locale.forLanguageTag("ru-RU")
                )
        );
        deleteProduct(purchaseEvent.getBoughtProduct());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
