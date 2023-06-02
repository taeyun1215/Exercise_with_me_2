package dev.ewm.stock.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.stock.adapter.in.request.AddStockRequest;
import dev.ewm.stock.application.port.in.AddStockUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class StockAddController {

    private final AddStockUseCase addStockUseCase;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ReturnObject> AddStock(
            @LoginUser User user,
            @PathVariable("productId") Long productId,
            @RequestBody AddStockRequest addStockRequest
    ) {
        addStockUseCase.AddStock(productId, addStockRequest);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품의 재고를 추가하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
