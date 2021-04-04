package ru.mirea.practice23.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice23.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
