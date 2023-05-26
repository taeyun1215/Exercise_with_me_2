package dev.ewm.mate.application.port.in;

import dev.ewm.user.domain.User;

public interface JoinMateUseCase {

    void joinMate(Long matePostId, User user);

}
