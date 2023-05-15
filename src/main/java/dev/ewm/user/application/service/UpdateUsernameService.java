package dev.ewm.user.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.usecase.UpdateUsernameUseCase;
import dev.ewm.user.application.port.out.UpdateUserStatePort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateUsernameService implements UpdateUsernameUseCase {

    private final UpdateUserStatePort updateUserStatePort;

    @Override
    public void updateUsername(User user, String username) {
        updateUserStatePort.updateUsername(user, username);
    }
}
