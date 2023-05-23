package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.adapter.in.dto.LoginUserRequest;
import dev.ewm.user.domain.User;

public interface LoginUserUseCase {

    User loginUser(LoginUserRequest loginUserCommand);

}
