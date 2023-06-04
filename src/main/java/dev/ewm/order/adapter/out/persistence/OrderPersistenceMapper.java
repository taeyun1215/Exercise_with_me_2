package dev.ewm.order.adapter.out.persistence;

import dev.ewm.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceMapper {

    public OrderJpaEntity mapToJpaEntity(Order order) {
        return order.toJpaEntity();
    }

}
