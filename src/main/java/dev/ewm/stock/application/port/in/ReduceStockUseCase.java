package dev.ewm.stock.application.port.in;

import dev.ewm.order.domain.OrderItem;

import java.util.List;

public interface ReduceStockUseCase {

    void reduceStock(OrderItem orderItem);

}
