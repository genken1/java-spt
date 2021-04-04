package ru.mirea.practice18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice18.models.Market;
import ru.mirea.practice18.repositories.MarketRepository;

import java.util.List;

@Service
public class MarketService {
    private MarketRepository marketRepository;

    @Transactional
    public void save(Market market) {
        marketRepository.save(market);
    }

    @Transactional
    public void remove(int id) {
        marketRepository.deleteById(id);
    }

    public List<Market> getSortedMarketsByField(String fieldName) {
        return marketRepository.findAll(Sort.by(fieldName));
    }

    public List<Market> getAll() {
        return marketRepository.findAll();
    }

    @Autowired
    private void setMarketRepository(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }
}
