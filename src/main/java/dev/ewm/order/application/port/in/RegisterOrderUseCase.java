package dev.ewm.order.application.port.in;

import dev.ewm.order.adapter.in.request.OrderRegisterRequest;
import dev.ewm.user.domain.User;

public interface RegisterOrderUseCase {

    void registerOrder(User user, OrderRegisterRequest orderRegisterRequest);

}
