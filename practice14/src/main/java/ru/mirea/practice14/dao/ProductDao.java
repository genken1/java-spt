package ru.mirea.practice14.dao;

import org.springframework.stereotype.Component;
import ru.mirea.practice14.Models.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao implements BaseOperations<Product> {
    private final List<Product> products = new ArrayList<>();

    @Override
    public boolean save(Product product) {
        return products.add(product);
    }

    @Override
    public boolean remove(String name) {
        return products.removeIf(el -> el.getName().equals(name));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
