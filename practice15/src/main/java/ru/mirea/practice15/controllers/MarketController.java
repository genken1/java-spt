package ru.mirea.practice15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice15.models.Market;
import ru.mirea.practice15.services.MarketService;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    private MarketService marketService;

    @PostMapping("/add")
    public Market add(
            @RequestParam String name,
            @RequestParam String address
    ) {
        Market market = null;
        if (!name.isEmpty() && !address.isEmpty()) {
            market = new Market();
            market.setName(name);
            market.setAddress(address);
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
