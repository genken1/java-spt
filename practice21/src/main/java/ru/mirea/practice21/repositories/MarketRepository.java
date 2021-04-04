package ru.mirea.practice21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice21.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
