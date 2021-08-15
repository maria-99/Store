package maria.products;

public class Soap extends Product {
    private static final long serialVersionUID = 1L;

    public Soap(String name, int price, int mass) {
        super(name, price, mass);
    }

    @Override
    public String getCategory() {
        return "Soaps";
    }
}
