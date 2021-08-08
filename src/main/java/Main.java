import Exceptions.*;
import Products.*;
import Stores.Store;
import Stores.StoreBuilder;

public class Main {
    public static void main(String[] args) {
        //проверка правильности работы
        //Book b1 = new Book("book 1", 25, 3);
        //Book b2 = new Book("book", 12, 7);
        Book b3 = new Book("strawberry ice-cream cake recipe", 12, 7);

        Soap s1 = new Soap("strawberry soap", 7, 6);

        Cake c1 = new Cake("strawberry ice-cream cake", 9, 8, 7);
        Cake c2 = new Cake("strawberry vanilla cake", 4, 4, 2);
        Cake c3 = new Cake("chocolate cake", 16, 2, 5);

        Store store = new StoreBuilder().setMaxSize(8).setName("Maria's").build();
        try {
            //store.addProduct(b1);
            //store.addProduct(b2);
            store.addProduct(b3);
            store.addProduct(s1);
            store.addProduct(c1);
            store.addProduct(c2);
            store.addProduct(c3);
        } catch (StoreFullException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("books: " + store.bookProducts());
        System.out.println("soaps: " + store.soapProducts());
        System.out.println("cakes: " + store.cakeProducts());

        System.out.println("Product List: " + store.getProductSet());
        System.out.println("Products like " + c1 + " are: " + store.findProductsLike(c1));
    }
}