package dev.ewm.stock.application.port.out;

import dev.ewm.stock.domain.Stock;

public interface SaveStockPort {

    void saveStock(Stock stock);

}
