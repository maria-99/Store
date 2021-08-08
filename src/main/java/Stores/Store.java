package Stores;

import Exceptions.IncorrectInputException;
import Exceptions.NoSuchProductException;
import Exceptions.StoreFullException;
import Products.Book;
import Products.Cake;
import Products.Product;
import Products.Soap;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Store implements CakeStore, SoapStore, BookStore, Serializable {

    private static final long serialVersionUID = 1L;
    private Set<Product> productSet = new HashSet<>();
    private final ProductSearchIndex productSI = new ProductSearchIndex();
    private int maxSize;
    private String name;

    public Store(int maxSize, String name) {
        this.maxSize = maxSize;
        this.name = name;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Store) {
            Store aStore = (Store) anObject;
            if(aStore.name.equals(name)
                    && aStore.maxSize == maxSize
                    && aStore.productSet.equals(productSet)) {
                return true;
            }
        }
        return false;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    //добавляет товар в список товаров магазина (если размер !> заданого макса)
    @Override
    public void addProduct(Product product) throws StoreFullException {
        if (productSet.size() < maxSize) {
            productSet.add(product);
            productSI.addProduct(product);
        } else {
            throw new StoreFullException(maxSize);
        }
    }

    //удаляет товары с наличием в названии указанного в параметре текста
    public void deleteProductsLike(String sample) throws NoSuchProductException {
        boolean deleted = productSet.removeIf(n -> {
            boolean b = n.getName().toLowerCase().contains(sample.toLowerCase());
            if (b) {
                productSI.deleteProduct(n);
            }
            return b;
        });
        if (!deleted) {
            throw new NoSuchProductException(sample);
        }
    }

    //возможность удалять товары по предикату товара
    public void deleteIf(Predicate<Product> predicate) {
        productSet.removeIf(n -> {
            boolean b = predicate.test(n);
            if (b){
                productSI.deleteProduct(n);
            }
            return b;
        });
    }

    //возвращает сколько товаров есть в магазине
    @Override
    public int productAmount() {
        return productSet.size();
    }

    //возвращает массив товаров меньше указанной в параметре метода цены
    public Set<Product> productsCheaper(int priceLimit) {
        Set<Product> productsCheaper = new HashSet<>();
        for (Product product : productSet) {
            if (product.getPrice() < priceLimit) {
                productsCheaper.add(product);
            }
        }
        return productsCheaper;
    }

    //возвращает массив товаров у которых срок годности не меньше параметра
    public Set<Product> shelfLifeNotLess(int minShelfLife) {
        Set<Product> shelfLifeLess = new HashSet<>();
        for (Product product : productSet) {
            if (product.getShelfLife() >= minShelfLife || product.getShelfLife() < 0) {
                shelfLifeLess.add(product);
            }
        }
        return shelfLifeLess;
    }

    private  <T extends Product> Set<T> productsOfType(String type){
        Set<T> productsOfCategory = new HashSet<>();
        for (Product product : productSet) {
            if (product.getCategory().equals(type)) {
                productsOfCategory.add((T) product);
            }
        }
        return productsOfCategory;
    }

    //возвращает массив всех книг
    @Override
    public Set<Book> bookProducts() {
        return productsOfType("Books");
    }

    //возвращает массив всех тортов
    @Override
    public Set<Cake> cakeProducts() {
        return productsOfType("Cakes");
    }

    //возвращает массив всех мыл
    @Override
    public Set<Soap> soapProducts() {
        return productsOfType("Soaps");
    }

    //сохраняет магазин в файл
    public void save(String fileName) throws IOException {
        File file = new File(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Object saving error!");
            throw e;
        }
    }

    //загружает магазин из файла
    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Store newStore = (Store) ois.readObject();
            maxSize = newStore.getMaxSize();
            productSet = newStore.productSet;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Object loading error!");
            throw e;
        }
    }

    //метод поиска, принимающий строку-шаблон для слов, в которой может быть ровно
    //один символ *; если он есть, то он мэтчит любое количество букв
    //или если нет "*"
    //возвращает товары с наличием в названии указанного в параметре слова (поиск по слову)
    //Выбрасывает ошибку если пользователь ввел некорректную строку (несколько "*")
    public Set<Product> productsLike(String sample) throws IncorrectInputException {
        Set<Product> set;
        if (sample.contains("*")) {
            if (sample.indexOf('*') == sample.lastIndexOf('*')) {
                set = productSI.findIf(str ->
                        str.startsWith(sample.substring(0, sample.indexOf('*')))
                                && str.endsWith(sample.substring(sample.indexOf('*') + 1))
                );
            } else {
                throw new IncorrectInputException("Only one '*' allowed in sample text!");
            }
        } else {
            set = productSI.findAll(sample);
        }
        return set;
    }

    public List<Product> findProductsLike(Product product){
        return productSI.findProductsLike(product);
    }

}
