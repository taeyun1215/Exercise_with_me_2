package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.domain.User;

public interface CheckUsernameUseCase {

    User checkUsername(String username);

}
