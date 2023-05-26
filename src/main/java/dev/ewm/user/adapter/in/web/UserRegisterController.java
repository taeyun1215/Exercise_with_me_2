package dev.ewm.user.adapter.in.web;

import dev.ewm.global.utils.ReturnObject;
import dev.ewm.user.adapter.in.request.RegisterUserRequest;
import dev.ewm.user.adapter.out.response.RegisterUserResponse;
import dev.ewm.user.adapter.out.persistence.UserResponseMapper;
import dev.ewm.user.application.port.in.usecase.RegisterUserUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRegisterController {

    private final RegisterUserUseCase registerUserUseCase;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerUser(
            @Validated @RequestBody RegisterUserRequest registerUserRequest
    ) {
        User user = registerUserUseCase.registerUser(registerUserRequest);
        RegisterUserResponse response = userResponseMapper.mapToRegisterUserResponse(user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
