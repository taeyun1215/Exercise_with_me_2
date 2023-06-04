package dev.ewm.order.adapter.in.request;

import dev.ewm.global.common.SelfValidating;
import dev.ewm.order.domain.OrderItem;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderItemRegisterRequest extends SelfValidating<OrderItemRegisterRequest> {

    private Long productId;
    private int count;

    public OrderItemRegisterRequest(Long productId, int count) {
        this.productId = productId;
        this.count = count;
        this.validateSelf();
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .productId(productId)
                .count(count)
                .build();
    }

}
