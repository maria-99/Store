package stores;

import products.Book;

import java.util.Set;

public interface BookStore extends GeneralStore {

    Set<Book> bookProducts();

}
