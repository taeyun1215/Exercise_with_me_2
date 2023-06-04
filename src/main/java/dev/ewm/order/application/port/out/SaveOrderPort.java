package dev.ewm.order.application.port.out;


import dev.ewm.order.domain.Order;

public interface SaveOrderPort {

    void saveOrder(Order order);

}
