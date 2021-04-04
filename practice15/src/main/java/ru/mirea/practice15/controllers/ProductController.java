package ru.mirea.practice15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice15.models.Product;
import ru.mirea.practice15.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    public Product add(
            @RequestParam String name,
            @RequestParam int price
    ) {
        Product product = null;
        if (!name.isEmpty()) {
            product = new Product();
            product.setName(name);
            product.setPrice(price);
            productService.save(product);
        }
        return product;
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("/remove")
    public int remove(@RequestParam int id) {
        productService.remove(id);
        return id;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
