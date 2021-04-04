package ru.mirea.practice14.dao;

import org.springframework.stereotype.Component;
import ru.mirea.practice14.Models.Market;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarketDao implements BaseOperations<Market> {
    private final List<Market> markets = new ArrayList<>();

    @Override
    public boolean save(Market market) {
        return markets.add(market);
    }

    @Override
    public boolean remove(String name) {
        return markets.removeIf(el -> el.getName().equals(name));
    }

    @Override
    public List<Market> getAll() {
        return markets;
    }

}
