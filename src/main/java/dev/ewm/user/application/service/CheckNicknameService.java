package dev.ewm.user.application.service;

import dev.ewm.domain.user.UserRepo;
import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.usecase.CheckNicknameUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CheckNicknameService implements CheckNicknameUseCase {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public User checkNickname(String nickname) {
        return userRepo.findByNickname(nickname);
    }

}
