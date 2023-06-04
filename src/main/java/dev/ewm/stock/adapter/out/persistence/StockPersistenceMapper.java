package dev.ewm.stock.adapter.out.persistence;

import dev.ewm.stock.domain.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockPersistenceMapper {

    public Stock mapToDomainEntity(StockJpaEntity stockJpaEntity) {
        return Stock.builder()
                .stockId(stockJpaEntity.getId())
                .productId(stockJpaEntity.getProductId())
                .quantity(stockJpaEntity.getQuantity())
                .build();
    }

    public StockJpaEntity mapToJpaEntity(Stock stock) {
        return stock.toJpaEntity();
    }

}
