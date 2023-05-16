package dev.ewm.user.adapter.out.response;

import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    // 회원가입 리스폰
    RegisterUserResponse mapToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 로그인 리스폰
    LoginUserResponse mapToLoginUserResponse(User user) {
        return LoginUserResponse.builder()
                .username(user.getUsername())
                .build();
    }

}
