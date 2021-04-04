package ru.mirea.practice20.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice20.models.Market;
import ru.mirea.practice20.repositories.MarketRepository;

import java.util.List;

@Service
@Slf4j
public class MarketService {
    private MarketRepository marketRepository;

    @Transactional
    public void save(Market market) {
        log.info("Save market");
        marketRepository.save(market);
    }

    @Transactional
    public void remove(int id) {
        log.info("Delete market with id: {}", id);
        marketRepository.deleteById(id);
    }

    public List<Market> getSortedMarketsByField(String fieldName) {
        log.info("Find all markets sorted by {}", fieldName);
        return marketRepository.findAll(Sort.by(fieldName));
    }

    public List<Market> getAll() {
        log.info("Find all markets");
        return marketRepository.findAll();
    }

    @Autowired
    private void setMarketRepository(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }
}
