package dev.ewm.stock.application.port.in;

import dev.ewm.stock.adapter.in.request.AddStockRequest;

public interface AddStockUseCase {

    void AddStock(Long productId, AddStockRequest addStockRequest);

}
