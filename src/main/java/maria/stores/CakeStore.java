package maria.stores;

import maria.products.Cake;

import java.util.Set;

public interface CakeStore extends GeneralStore {

    Set<Cake> cakeProducts();
}
