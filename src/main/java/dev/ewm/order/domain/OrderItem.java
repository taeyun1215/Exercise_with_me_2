package dev.ewm.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private Long OrderItemId;
    private Long productId;
    private int price;
    private int count;

}
