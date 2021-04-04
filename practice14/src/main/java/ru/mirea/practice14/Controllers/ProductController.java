package ru.mirea.practice14.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice14.Models.Product;
import ru.mirea.practice14.dao.ProductDao;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductDao productDao;

    @PostMapping("/add")
    @ResponseBody
    public Product add(
            @RequestParam String name,
            @RequestParam int price
    ) {
        Product product = null;
        if (!name.isEmpty()) {
            product = new Product();
            product.setName(name);
            product.setPrice(price);
            productDao.save(product);
        }
        return product;
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public boolean remove(@RequestParam String name) {
        return productDao.remove(name);
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
