package ru.mirea.practice18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice18.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
