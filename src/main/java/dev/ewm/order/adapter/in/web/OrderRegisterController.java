package dev.ewm.order.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.order.adapter.in.request.OrderRegisterRequest;
import dev.ewm.order.application.port.in.RegisterOrderUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderRegisterController {

    private final RegisterOrderUseCase registerOrderUseCase;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ReturnObject> AddStock(
            @LoginUser User user,
            @RequestBody OrderRegisterRequest orderRegisterRequest
    ) {
        registerOrderUseCase.registerOrder(user, orderRegisterRequest);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품의 재고를 추가하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
