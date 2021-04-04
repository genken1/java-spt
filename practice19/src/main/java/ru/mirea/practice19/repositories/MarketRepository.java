package ru.mirea.practice19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice19.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
