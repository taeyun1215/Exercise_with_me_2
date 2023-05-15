package dev.ewm.user.application.port.in.query;

import dev.ewm.user.domain.User;

public interface CheckUsernameQuery {

    User checkUsername(String username);

}
