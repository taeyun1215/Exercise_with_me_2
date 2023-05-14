package dev.ewm.user.application.port.in.usecase;

import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.user.domain.User;

public interface RegisterUserUseCase {

    User registerUser(UserRegisterRequest userRegisterRequest);

}
