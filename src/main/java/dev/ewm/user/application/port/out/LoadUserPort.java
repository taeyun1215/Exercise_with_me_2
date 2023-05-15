package dev.ewm.user.application.port.out;

import dev.ewm.user.domain.User;

public interface LoadUserPort {

    User findByUsername(String username);
    User findByNickname(String nickname);

}
