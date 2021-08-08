package Stores;

public class StoreBuilder {
    private String name;
    private int maxSize;

    public StoreBuilder setName(String name) {
        if(name.equals("")){
            throw new IllegalArgumentException("Store name cant be empty!");
        }
        this.name = name;
        return this;
    }

    public StoreBuilder setMaxSize(int maxSize) {
        if(maxSize < 1){
            throw new IllegalArgumentException("Store size must be positive!");
        }
        this.maxSize = maxSize;
        return this;
    }

    public Store build() {
        if(name == null || maxSize == 0){
            throw new IllegalStateException("Not all Store parameters filled!");
        }
        return new Store(maxSize, name);
    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
