package dev.ewm.product.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.product.adapter.in.request.RegisterProductRequest;
import dev.ewm.product.application.port.in.RegisterProductUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductRegisterController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerProduct(
            @LoginUser User user,
            @RequestBody RegisterProductRequest registerUserRequest
    ) {
        registerProductUseCase.registerProduct(registerUserRequest, user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품 추가가 완료 되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
