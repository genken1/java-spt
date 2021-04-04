package ru.mirea.practice18.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice18.models.Product;
import ru.mirea.practice18.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    public Product add(@RequestParam String name, @RequestParam int price, @RequestParam int marketId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        if (!product.getName().isEmpty()) {
            productService.save(product, marketId);
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

    @GetMapping(value = "/sorted-by-name")
    public List<Product> getSortedProductByName() {
        return productService.getSortedProductsByField("name");
    }

    @GetMapping(value = "/sorted-by-price")
    public List<Product> getSortedProductByPrice() {
        return productService.getSortedProductsByField("price");
    }

    @GetMapping(value = "/{marketId}/market") public @ResponseBody
    List<Product> getAllProductsByMarket(@PathVariable("marketId") int marketId){
        return productService.getAllProductsByMarketId(marketId);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
