package ru.mirea.practice23.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice23.models.Market;
import ru.mirea.practice23.repositories.MarketRepository;

import java.util.List;

@Service
@Slf4j
public class MarketService {
    private MarketRepository marketRepository;
    private EmailService emailService;

    @Transactional
    public void save(Market market) {
        log.info("Save market");
        emailService.sendEmail("Save Market", market.getName());
        marketRepository.save(market);
    }

    @Transactional
    public void remove(int id) {
        log.info("Delete market with id: {}", id);
        marketRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Market> getSortedMarketsByField(String fieldName) {
        log.info("Find all markets sorted by {}", fieldName);
        return marketRepository.findAll(Sort.by(fieldName));
    }

    @Transactional(readOnly = true)
    public List<Market> getAll() {
        log.info("Find all markets");
        List<Market> list = marketRepository.findAll();
        return list;
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
