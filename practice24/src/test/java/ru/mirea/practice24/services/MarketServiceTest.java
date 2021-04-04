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
import ru.mirea.practice24.repositories.MarketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MarketServiceTest {
    @MockBean
    MarketRepository marketRepository;

    private MarketService marketService;

    @Captor
    private ArgumentCaptor<Market> captor;

    private final List<Market> markets = new ArrayList<>();

    @Autowired
    public void setMarketService(MarketService marketService) {
        this.marketService = marketService;
    }

    @Before
    public void setUp() {
        Market market1 = new Market();
        market1.setName("store");
        market1.setAddress("address1");

        Market market2 = new Market();
        market2.setName("store2");
        market2.setAddress("address2");
        markets.add(market1);
        markets.add(market2);

        when(marketRepository.findAll())
                .thenReturn(markets);
    }

    @Test
    public void whenGetAll() {
        List<Market> fetched = marketService.getAll();
        verify(marketRepository, times(1))
                .findAll();
        assertEquals(markets.size(), fetched.size());
    }

    @Test
    public void whenSave() {
        Market market = markets.get(0);
        marketService.save(market);
        verify(marketRepository).save(captor.capture());
        assertEquals(market.getName(), captor.getValue().getName());
        assertEquals(market.getAddress(), captor.getValue().getAddress());
        assertEquals(market.getId(), captor.getValue().getId());
    }

    @Test
    public void whenRemove() {
        Market market = markets.get(0);
        marketService.save(market);

        verify(marketRepository, times(1))
                .save(market);
        verify(marketRepository).save(captor.capture());
        Market captured = captor.getValue();

        when(marketRepository.findById(captured.getId()))
                .thenReturn(Optional.of(captured));

        marketService.remove(captured.getId());
        verify(marketRepository, times(1))
                .deleteById(captured.getId());
    }
}
