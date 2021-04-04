package ru.mirea.practice24.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice24.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
