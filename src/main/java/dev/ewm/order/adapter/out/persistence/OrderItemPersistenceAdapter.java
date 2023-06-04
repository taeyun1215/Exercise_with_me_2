package dev.ewm.order.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.order.application.port.out.SaveOrderItemPort;
import dev.ewm.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderItemPersistenceAdapter implements SaveOrderItemPort {

    private final OrderItemJpaRepo orderItemJpaRepo;
    private final OrderItemPersistenceMapper orderItemPersistenceMapper;

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemJpaRepo.save(orderItemPersistenceMapper.mapToJpaEntity(orderItem));
    }

}
