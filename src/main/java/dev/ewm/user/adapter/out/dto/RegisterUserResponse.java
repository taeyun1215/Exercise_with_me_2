package dev.ewm.user.adapter.out.dto;

import dev.ewm.user.domain.constant.Role;
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

}
