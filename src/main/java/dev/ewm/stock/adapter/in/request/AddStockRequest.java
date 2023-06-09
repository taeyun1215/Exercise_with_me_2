package dev.ewm.stock.adapter.in.request;

import dev.ewm.global.common.SelfValidating;
import dev.ewm.stock.domain.Stock;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddStockRequest extends SelfValidating<AddStockRequest> {

    @Range(min = 1, message = "재고 수량은 최소 1개 이상이어야 합니다")
    private int quantity;

    public AddStockRequest(int quantity) {
        this.quantity = quantity;
    }

    public Stock toEntity(Long productId) {
        return Stock.builder()
                .quantity(quantity)
                .productId(productId)
                .build();
    }

}
