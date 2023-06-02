package dev.ewm.stock.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.product.domain.Product;
import dev.ewm.stock.adapter.in.request.AddStockRequest;
import dev.ewm.stock.application.port.in.AddStockUseCase;
import dev.ewm.stock.application.port.out.SaveStockPort;
import dev.ewm.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class AddStockService implements AddStockUseCase {

    private final SaveStockPort saveStockPort;

    @Override
    public void AddStock(Long productId, AddStockRequest addStockRequest) {
        Stock stock = addStockRequest.toEntity(productId);
        saveStockPort.saveStock(stock);
    }
}
