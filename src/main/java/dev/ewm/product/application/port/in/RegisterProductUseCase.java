package dev.ewm.product.application.port.in;

import dev.ewm.product.adapter.in.request.RegisterProductRequest;
import dev.ewm.user.domain.User;

public interface RegisterProductUseCase {

    void registerProduct(RegisterProductRequest registerProductRequest, User user);

}
