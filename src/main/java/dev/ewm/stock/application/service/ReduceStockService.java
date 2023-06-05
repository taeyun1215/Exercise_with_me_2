package dev.ewm.stock.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import dev.ewm.stock.application.port.out.LoadStockPort;
import dev.ewm.stock.application.port.out.SaveStockPort;
import dev.ewm.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ReduceStockService implements ReduceStockUseCase {

    private final LoadStockPort loadStockPort;
    private final SaveStockPort saveStockPort;

    @Override
    public void reduceStock(OrderItem orderItem) {
        Stock stock = loadStockPort.loadStock(orderItem.getProductId());
        if (stock == null) {
            throw new EntityNotFoundException("상품이 없습니다.");
        }
        if (stock.getQuantity() < orderItem.getCount()) {
            throw new RuntimeException("수량이 없습니다.");
        } else {
            stock.reduceStock(orderItem.getCount());
            saveStockPort.saveStock(stock);
        }
    }
}
