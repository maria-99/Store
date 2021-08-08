package Stores;

import Exceptions.StoreFullException;
import Products.Product;

public interface GeneralStore {

    void addProduct(Product product) throws StoreFullException;
    int productAmount();
}