package dev.ewm.product.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.product.adapter.in.request.RegisterProductRequest;
import dev.ewm.product.application.port.in.RegisterProductUseCase;
import dev.ewm.product.application.port.out.SaveProductPort;
import dev.ewm.product.domain.Product;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public void registerProduct(RegisterProductRequest registerProductRequest, User user) {
        Product product = registerProductRequest.toEntity(user);
        saveProductPort.saveProduct(product);
    }
}
