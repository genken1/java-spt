package ru.mirea.practice24.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mirea.practice24.models.Market;
import ru.mirea.practice24.models.Product;
import ru.mirea.practice24.repositories.MarketRepository;
import ru.mirea.practice24.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private MarketRepository marketRepository;

    @Captor
    private ArgumentCaptor<Product> captor;
    private Market market;
    private ProductService productService;
    private final List<Product> products = new ArrayList<>();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Before
    public void setUp() {
        market = new Market();
        market.setId(1);
        market.setName("market");
        market.setAddress("address");

        when(marketRepository.findById(market.getId()))
                .thenReturn(Optional.of(market));

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Milk");
        product1.setPrice(500);
        product1.setMarket(market);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Eggs");
        product2.setPrice(100);
        product2.setMarket(market);

        products.add(product1);
        products.add(product2);

        when(productRepository.findAll())
                .thenReturn(products);
    }

    @Test
    public void whenGetAll() {
        List<Product> fetched = productService.getAll();
        verify(productRepository, times(1))
                .findAll();
        assertEquals(products.size(), fetched.size());
    }

    @Test
    public void whenValidSave() {
        Product product = new Product();
        product.setName("Milk");
        product.setPrice(88);
        product.setMarket(market);
        productService.save(product, market.getId());

        verify(productRepository).save(captor.capture());
        Product captured = captor.getValue();
        assertEquals(product.getName(), captured.getName());
        assertEquals(product.getPrice(), captured.getPrice());
        assertEquals(product.getMarket().getId(), captured.getMarket().getId());
    }

    @Test
    public void whenDelete() {
        Product product = new Product();
        product.setName("Milk");
        product.setPrice(88);
        product.setMarket(market);
        productService.save(product, market.getId());

        verify(productRepository).save(captor.capture());
        Product captured = captor.getValue();

        when(productRepository.findById(captured.getId()))
                .thenReturn(Optional.of(captured));

        productService.remove(captured.getId());
        verify(productRepository, times(1))
                .deleteById(captured.getId());
    }
}
