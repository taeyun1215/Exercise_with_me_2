package dev.ewm.order.adapter.out.persistence;

import dev.ewm.order.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemPersistenceMapper {

    public OrderItemJpaEntity mapToJpaEntity(OrderItem orderItem) {
        return orderItem.toJpaEntity();
    }

}
