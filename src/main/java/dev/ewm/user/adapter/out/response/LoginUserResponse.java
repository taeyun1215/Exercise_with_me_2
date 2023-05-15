package dev.ewm.user.adapter.out.response;

import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserResponse {

    private String username;

    public static LoginUserResponse from(User user) {
        return LoginUserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
