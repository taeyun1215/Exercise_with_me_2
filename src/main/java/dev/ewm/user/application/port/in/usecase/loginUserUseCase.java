package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.application.port.in.command.LoginUserCommand;
import dev.ewm.user.domain.User;

public interface loginUserUseCase {

    User loginUser(LoginUserCommand loginUserCommand);

}
