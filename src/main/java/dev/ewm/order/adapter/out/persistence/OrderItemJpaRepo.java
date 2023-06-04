package dev.ewm.order.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepo extends JpaRepository<OrderItemJpaEntity, Long> {
}
