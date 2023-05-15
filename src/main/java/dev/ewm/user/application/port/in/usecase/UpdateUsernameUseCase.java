package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.domain.User;

public interface UpdateUsernameUseCase {

    void updateUsername(User user, String username);

}
