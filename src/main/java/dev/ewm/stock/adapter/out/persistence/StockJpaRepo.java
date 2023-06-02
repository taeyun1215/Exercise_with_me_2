package dev.ewm.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepo extends JpaRepository<StockJpaEntity, Long> {
}
