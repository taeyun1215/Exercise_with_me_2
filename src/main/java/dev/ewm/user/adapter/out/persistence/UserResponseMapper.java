package dev.ewm.user.adapter.out.persistence;

import dev.ewm.user.adapter.out.dto.LoginUserResponse;
import dev.ewm.user.adapter.out.dto.RegisterUserResponse;
import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    // 회원가입 리스폰
    public RegisterUserResponse mapToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 로그인 리스폰
    public LoginUserResponse mapToLoginUserResponse(User user) {
        return LoginUserResponse.builder()
                .username(user.getUsername())
                .build();
    }

}
