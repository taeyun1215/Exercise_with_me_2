package dev.ewm.user.adapter.out.response;

import dev.ewm.user.domain.constant.Role;
import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserResponse {

    private String username;
    private String nickname;
    private String phone;
    private String email;
    private Role role;

    public static RegisterUserResponse from(User user) {
        return RegisterUserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
