package ru.mirea.practice20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice20.models.Market;
import ru.mirea.practice20.services.MarketService;

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

    @GetMapping(value = "/sorted-by-name")
    public List<Market> getSortedProductByName() {
        return marketService.getSortedMarketsByField("name");
    }

    @GetMapping(value = "/sorted-by-address")
    public List<Market> getSortedProductByAddress() {
        return marketService.getSortedMarketsByField(("address"));
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
