package dev.ewm.order.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.order.adapter.in.request.OrderItemRegisterRequest;
import dev.ewm.order.adapter.in.request.OrderRegisterRequest;
import dev.ewm.order.application.port.in.RegisterOrderUseCase;
import dev.ewm.order.application.port.out.SaveOrderItemPort;
import dev.ewm.order.application.port.out.SaveOrderPort;
import dev.ewm.order.domain.Order;
import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements RegisterOrderUseCase {

    private final ReduceStockUseCase reduceStockUseCase;
    private final SaveOrderPort saveOrderPort;
    private final SaveOrderItemPort saveOrderItemPort;

    @Override
    public void registerOrder(User user, OrderRegisterRequest orderRegisterRequest) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRegisterRequest orderItemRegisterRequest : orderRegisterRequest.getOrderItemRegisterRequests()) {
            OrderItem orderItem = orderItemRegisterRequest.toEntity();
            orderItems.add(orderItem);
        }
        Order order = orderRegisterRequest.toEntity(orderItems, user);
        validateOrder(order);

        for (OrderItem orderItem : orderItems) {
            reduceStockUseCase.reduceStock(orderItem);
        }
        save(order, orderItems);
    }

    private void validateOrder(Order order) {
        if (order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("주문할 제품을 입력하세요.");
        }
    }

    private void save(Order order, List<OrderItem> orderItems) {
        saveOrderPort.saveOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getOrderId());
            saveOrderItemPort.saveOrderItem(orderItem);
        }
    }

}
