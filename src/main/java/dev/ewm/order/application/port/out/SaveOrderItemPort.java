package dev.ewm.order.application.port.out;

import dev.ewm.order.domain.OrderItem;

public interface SaveOrderItemPort {

    void saveOrderItem(OrderItem orderItem);

}
