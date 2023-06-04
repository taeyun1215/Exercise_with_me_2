package dev.ewm.order.domain;

import dev.ewm.order.adapter.out.persistence.OrderJpaEntity;
import dev.ewm.order.domain.constant.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Long userId;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderJpaEntity toJpaEntity() {
        return OrderJpaEntity.builder()
                .id(orderId)
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .userId(userId)
                .orderStatus(orderStatus)
                .orderItemJpaEntities(
                        orderItems
                                .stream()
                                .map(OrderItem::toJpaEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
