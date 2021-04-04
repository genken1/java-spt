package ru.mirea.practice14.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice14.Models.Market;
import ru.mirea.practice14.dao.MarketDao;

import java.util.List;

@Controller
@RequestMapping("/market")
public class MarketController {
    MarketDao marketDao;

    @PostMapping("/add")
    @ResponseBody
    public Market add(
            @RequestParam String name,
            @RequestParam String address
    ) {
        Market market = null;
        if (!name.isEmpty() && !address.isEmpty()) {
            market = new Market();
            market.setName(name);
            market.setAddress(address);
            marketDao.save(market);
        }
        return market;
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Market> getAll() {
        return marketDao.getAll();
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public boolean remove(@RequestParam String name) {
        return marketDao.remove(name);
    }

    @Autowired
    public void setMarketDao(MarketDao marketDao) {
        this.marketDao = marketDao;
    }
}
