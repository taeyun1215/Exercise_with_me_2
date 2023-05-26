package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.adapter.in.request.RegisterUserRequest;
import dev.ewm.user.domain.User;

public interface RegisterUserUseCase {

    User registerUser(RegisterUserRequest registerUserRequest);

}
