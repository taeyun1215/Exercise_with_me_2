package dev.ewm.stock.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.stock.application.port.out.SaveStockPort;
import dev.ewm.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class StockPersistenceAdapter implements SaveStockPort {

    private StockJpaRepo stockJpaRepo;
    private StockPersistenceMapper stockPersistenceMapper;

    @Override
    public void saveStock(Stock stock) {
        stockJpaRepo.save(stockPersistenceMapper.mapToJpaEntity(stock));
    }
}
