package ru.mirea.practice20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice20.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
