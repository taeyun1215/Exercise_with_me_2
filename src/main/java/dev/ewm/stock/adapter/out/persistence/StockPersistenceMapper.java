package dev.ewm.stock.adapter.out.persistence;

import dev.ewm.stock.domain.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockPersistenceMapper {

    public StockJpaEntity mapToJpaEntity(Stock stock) {
        return stock.toJpaEntity();
    }
}
