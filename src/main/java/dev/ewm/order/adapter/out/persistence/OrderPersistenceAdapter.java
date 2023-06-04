package dev.ewm.order.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.order.application.port.out.SaveOrderPort;
import dev.ewm.order.domain.Order;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements
        SaveOrderPort {

    private final OrderJpaRepo orderJpaRepo;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public void saveOrder(Order order) {
        orderJpaRepo.save(orderPersistenceMapper.mapToJpaEntity(order));
    }

}
