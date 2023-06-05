package dev.ewm.stock.config;

import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import dev.ewm.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final ReduceStockUseCase reduceStockUseCase;
    private final PlatformTransactionManager transactionManager;

    public void reduceStock(OrderItem orderItem) throws InterruptedException {

        RLock lock = redissonClient.getLock(orderItem.getProductId().toString());
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("lock 획득에 실패하였습니다");
                return;
            }

            TransactionStatus status = transactionManager.getTransaction(null);
            reduceStockUseCase.reduceStock(orderItem);
            transactionManager.commit(status);
        } finally {
            lock.unlock();
        }
    }
}
