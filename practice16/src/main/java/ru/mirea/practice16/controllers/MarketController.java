package ru.mirea.practice16.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice16.models.Market;
import ru.mirea.practice16.services.MarketService;

import java.util.List;


@RestController
@RequestMapping("/market")
public class MarketController {
    MarketService marketService;

    @PostMapping("/add")
    public Market add(@RequestBody Market market) {
        if (!market.getName().isEmpty() && !market.getAddress().isEmpty()) {
            marketService.save(market);
        }
        return market;
    }

    @GetMapping("/getAll")
    public List<Market> getAll() {
        return marketService.getAll();
    }

    @DeleteMapping("/remove")
    public int remove(@RequestParam int id)
    {
        marketService.remove(id);
        return id;
    }

    @Autowired
    public void setMarketServices(MarketService marketServices) {
        marketService = marketServices;
    }
}
