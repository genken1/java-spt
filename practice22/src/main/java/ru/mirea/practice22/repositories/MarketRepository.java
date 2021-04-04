package ru.mirea.practice22.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice22.models.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
