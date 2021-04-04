package ru.mirea.practice21.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice21.models.Market;
import ru.mirea.practice21.models.Product;
import ru.mirea.practice21.repositories.MarketRepository;
import ru.mirea.practice21.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private ProductRepository productRepository;
    private MarketRepository marketRepository;
    private EmailService emailService;

    @Transactional
    public void save(Product product, int marketId) {
        log.info("Save product");
        Optional<Market> market = marketRepository.findById(marketId);
        if(market.isPresent()) {
            product.setMarket(market.get());
            productRepository.save(product);
        }
        emailService.sendEmail("Save Product", product.toString());
    }

    @Transactional
    public void remove(int id) {
        log.info("Delete product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getSortedProductsByField(String fieldName) {
        log.info("Find all products sorted by {}", fieldName);
        return productRepository.findAll(Sort.by(fieldName));
    }

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        log.info("Find all products");
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProductsByMarketId(int marketId) {
        log.info("Find all products by market id: {}", marketId);
        return productRepository.findAllByMarketId(marketId);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setMarketRepository(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
