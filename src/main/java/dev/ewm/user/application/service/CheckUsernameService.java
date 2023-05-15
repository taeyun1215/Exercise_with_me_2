package dev.ewm.user.application.service;

import dev.ewm.domain.user.UserRepo;
import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.usecase.CheckUsernameUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CheckUsernameService implements CheckUsernameUseCase {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public User checkUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
