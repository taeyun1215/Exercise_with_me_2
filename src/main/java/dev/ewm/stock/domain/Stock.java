package dev.ewm.stock.domain;

import dev.ewm.stock.adapter.out.persistence.StockJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    private Long stockId;
    private int quantity;

    private Long productId;

    public StockJpaEntity toJpaEntity() {
        return StockJpaEntity.builder()
                .id(stockId)
                .quantity(quantity)
                .productId(productId)
                .build();
    }

}
