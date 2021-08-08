package Stores;

import Products.Product;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductSearchIndex {
     final Map<String, Set<Product>> searchMap = new HashMap<>();

    public void addProduct(Product product){
        String[] keyWords = product.getName().split(" ");
        for(String keyWord: keyWords){
            searchMap.computeIfAbsent(keyWord, k -> new HashSet<>()).add(product);
        }
    }

    public void deleteProduct(Product product){
        String[] keyWords = product.getName().split(" ");
        for(String keyWord: keyWords){
            searchMap.get(keyWord).remove(product);
            if(searchMap.get(keyWord).isEmpty()){
                searchMap.remove(keyWord);
            }
        }
    }

    //Добавьте в класс поискового индекса возможность удалять товары по предикату товара
    public void deleteIf(Predicate<Product> predicate){
        Set<String> keywords = new HashSet<>(searchMap.keySet());
        for (String keyWord: keywords) {
            searchMap.get(keyWord).removeIf(predicate);
            if(searchMap.get(keyWord).isEmpty()){
                searchMap.remove(keyWord);
            }
        }
    }

    //возвращает список по ключевому слову, если такого нет возвращает пустой список
    public Set<Product> findAll(String keyWord){
        return searchMap.getOrDefault(keyWord, new HashSet<>());
    }

    //метод поиска, принимающий не слово, а предикат для слова
    public Set<Product> findIf(Predicate<String> predicate){
        Set<Product> acceptable = new HashSet<>();
        for (String keyWord: searchMap.keySet()) {
            if (predicate.test(keyWord)){
                acceptable.addAll(searchMap.get(keyWord));
            }
        }
        return acceptable;
    }

    //получить стрим из всех товаров, которые могут находиться вместе с ним при поиске по слову,
    //сперва шли товары, наиболее часто встречающиеся рядом с указанным товаром
    public List<Product> findProductsLike(Product product){
        List<String> keywords = Arrays.asList(product.getName().split(" "));
        List<Product> collected = keywords.stream()
                .map(searchMap::get)
                .flatMap(Collection::stream)
                .filter(p -> !p.equals(product))
                .collect(Collectors.toList());

        return collected.stream()
                .sorted(Comparator.comparingInt(p -> Collections.frequency(collected,p)).reversed())
                .distinct()
                .collect(Collectors.toList());
    }

}
