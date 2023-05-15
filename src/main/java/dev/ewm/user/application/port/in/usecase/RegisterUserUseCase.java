package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.application.port.in.command.RegisterUserCommand;
import dev.ewm.user.domain.User;

public interface RegisterUserUseCase {

    User registerUser(RegisterUserCommand registerUserCommand);

}
