package dev.ewm.stock.config;

import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

    private final ReduceStockUseCase reduceStockUseCase;
    private final PlatformTransactionManager platformTransactionManager;

    public void reduceStock(OrderItem orderItem) throws InterruptedException {
        try {
            TransactionStatus status = platformTransactionManager.getTransaction(null);
            reduceStockUseCase.reduceStock(orderItem);
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            Thread.sleep(50);
        }
    }
}
