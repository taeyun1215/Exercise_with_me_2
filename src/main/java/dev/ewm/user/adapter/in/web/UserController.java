package dev.ewm.user.adapter.in.web;

import dev.ewm.domain.user.UserService;
import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.user.adapter.out.response.LoginUserResponse;
import dev.ewm.user.adapter.out.response.RegisterUserResponse;
import dev.ewm.global.error.ErrorCode;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.user.adapter.out.response.UserResponseMapper;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerUser(
            @Validated @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        User user = userService.registerUser(userRegisterRequest);
        RegisterUserResponse response = RegisterUserResponse.from(user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<ReturnObject> checkUsername(
            @PathVariable("username") String username
    ) {
        User user = userService.checkUsername(username);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(username)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

    @GetMapping("/nickname/{nickname}/exists")
    public ResponseEntity<ReturnObject> checkNickname(
            @PathVariable("nickname") String nickname
    ) {
        User user = userService.checkNickname(nickname);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(nickname)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnObject> loginUser(
            @Validated @RequestBody UserLoginRequest userLoginRequest,
            HttpServletRequest request
    ) {
        User user = userService.loginUser(userLoginRequest);
        LoginUserResponse response = LoginUserResponse.from(user);

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, response);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @PostMapping("/logout")
    public ResponseEntity<ReturnObject> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
