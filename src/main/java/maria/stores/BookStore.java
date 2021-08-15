package maria.stores;

import maria.products.Book;

import java.util.Set;

public interface BookStore extends GeneralStore {

    Set<Book> bookProducts();

}
