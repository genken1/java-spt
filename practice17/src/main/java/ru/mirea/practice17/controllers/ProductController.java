package ru.mirea.practice17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice17.models.Market;
import ru.mirea.practice17.models.Product;
import ru.mirea.practice17.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    public Product add(@RequestBody Product product) {
        if (!product.getName().isEmpty()) {
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

    @GetMapping(value = "/sorted-by-name")
    public List<Product> getSortedProductByName() {
        return productService.getSortedProductsByField("name");
    }

    @GetMapping(value = "/sorted-by-price")
    public List<Product> getSortedProductByPrice() {
        return productService.getSortedProductsByField("price");
    }

    @GetMapping(value = "/{productId}/market") public @ResponseBody
    Market getProductMarket(@PathVariable("productId") int productId){
        return productService.getMarketByProduct(productId);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
