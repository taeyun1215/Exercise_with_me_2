package dev.ewm;

import dev.ewm.order.domain.OrderItem;
import dev.ewm.stock.adapter.out.persistence.StockJpaEntity;
import dev.ewm.stock.adapter.out.persistence.StockJpaRepo;
import dev.ewm.stock.application.port.in.ReduceStockUseCase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private StockJpaRepo stockJpaRepo;

    @Autowired
    private ReduceStockUseCase reduceStockUseCase;

    // SpringBoot 프로젝트에서는 PlatformTransactionManager 빈이 자동으로 등록된다. 따라서 주입하기만 하면 된다.
    @Autowired
    private PlatformTransactionManager transactionManager;

    OrderItem orderItem;
    List<OrderItem> orderList = new ArrayList<>();

    @BeforeEach
    public void init() {
        stockJpaRepo.save(new StockJpaEntity(1L, 100, 1L));
        orderItem = new OrderItem(1L, 1L, 1, 1L);
    }

    @AfterEach
    public void clear() {
        // 테스트용 데이터 삭제
        stockJpaRepo.deleteAll();
    }

    @Test
    @DisplayName("100명이 동시에 1개씩 재고 감소시키기")
    public void AtTheSameTime_100Requests() throws InterruptedException {
        //given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        IntStream.range(0,100).forEach(e -> executorService.submit(() -> {
                    try{
                        TransactionStatus status =  transactionManager.getTransaction(null);
                        reduceStockUseCase.reduceStock(orderItem);
                        transactionManager.commit(status);
                    } finally {
                        latch.countDown();
                    }
                }
        ));
        latch.await();

        // then
        StockJpaEntity stock = stockJpaRepo.findByProductId(1L);
        assertEquals(0L, stock.getQuantity());
    }

}
