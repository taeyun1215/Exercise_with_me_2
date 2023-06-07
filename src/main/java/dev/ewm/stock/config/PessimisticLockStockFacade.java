package dev.ewm.stock.config;

import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessimisticLockStockFacade {

    private final ReduceStockUseCase reduceStockUseCase;

    public void reduceStock(OrderItem orderItem) throws InterruptedException {
        while (true) {
            try {
                reduceStockUseCase.reduceStock(orderItem);
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
