package dev.ewm.user.adapter.in.web;

import dev.ewm.global.utils.ReturnObject;
import dev.ewm.user.adapter.in.dto.request.LoginUserRequest;
import dev.ewm.user.adapter.in.dto.response.LoginUserResponse;
import dev.ewm.user.adapter.out.persistence.UserResponseMapper;
import dev.ewm.user.application.port.in.usecase.LoginUserUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserLoginController {

    private final LoginUserUseCase loginUserUseCase;
    private final UserResponseMapper userResponseMapper;

    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    @PostMapping("/login")
    public ResponseEntity<ReturnObject> loginUser(
            @Validated @RequestBody LoginUserRequest loginUserRequest,
            HttpServletRequest request
    ) {
        User user = loginUserUseCase.loginUser(loginUserRequest);
        LoginUserResponse response = userResponseMapper.mapToLoginUserResponse(user);

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, response);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
