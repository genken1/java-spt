package ru.mirea.practice18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice18.models.Market;
import ru.mirea.practice18.models.Product;
import ru.mirea.practice18.repositories.MarketRepository;
import ru.mirea.practice18.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private MarketRepository marketRepository;

    @Transactional
    public void save(Product product, int marketId) {
        Optional<Market> market = marketRepository.findById(marketId);
        if(market.isPresent()) {
            product.setMarket(market.get());
            productRepository.save(product);
        }
    }

    @Transactional
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getSortedProductsByField(String fieldName) {
        return productRepository.findAll(Sort.by(fieldName));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByMarketId(int marketID) {
        return productRepository.findAllByMarketId(marketID);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setMarketRepository(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }
}
