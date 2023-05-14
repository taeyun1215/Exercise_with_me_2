package dev.ewm.user.application.port.in.usecase;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.user.domain.User;

public interface loginUserUseCase {

    User loginUser(UserLoginRequest userLoginRequest);

}
