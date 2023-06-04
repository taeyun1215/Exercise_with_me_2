package dev.ewm.order.domain;

import dev.ewm.order.adapter.out.persistence.OrderItemJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private Long orderItemId;
    private Long productId;
    private int count;
    private Long orderId;

    public OrderItemJpaEntity toJpaEntity() {
        return OrderItemJpaEntity.builder()
                .id(orderItemId)
                .productId(productId)
                .count(count)
                .OrderId(orderId)
                .build();
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
