package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.adapter.in.request.RegisterUserRequest;

public interface RegisterUserUseCase {

    void registerUser(RegisterUserRequest registerUserCommand);

}
