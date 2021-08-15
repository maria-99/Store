package maria.products;

public class Cake extends Product implements ProductWithShelfLife {
    private static final long serialVersionUID = 1L;

    public Cake(String name, int price, int mass, int shelfLife) {
        super(name, price, mass, shelfLife);
    }

    @Override
    public String getCategory() {
        return "Cakes";
    }
}
