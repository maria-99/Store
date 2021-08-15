package maria.products;

public class Book extends Product {
    private static final long serialVersionUID = 1L;

    public Book(String name, int price, int mass) {
        super(name, price, mass);
    }

    @Override
    public String getCategory() {
        return "Books";
    }
}