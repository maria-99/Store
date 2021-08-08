package Stores;

import Products.Book;

import java.util.Set;

public interface BookStore extends GeneralStore {

    Set<Book> bookProducts();

}
