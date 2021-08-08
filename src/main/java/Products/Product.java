package Products;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private final String name;
    private final int price, mass;
    private int shelfLife = -1;

    public Product(String name, int price, int mass) {
        this.name = name;
        this.price = price;
        this.mass = mass;
    }

    public Product(String name, int price, int mass, int shelfLife) {
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.shelfLife = shelfLife;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMass() {
        return mass;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public abstract String getCategory();

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}