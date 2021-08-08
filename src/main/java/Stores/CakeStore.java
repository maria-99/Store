package Stores;

import Products.Cake;

import java.util.Set;

public interface CakeStore extends GeneralStore {

    Set<Cake> cakeProducts();
}
