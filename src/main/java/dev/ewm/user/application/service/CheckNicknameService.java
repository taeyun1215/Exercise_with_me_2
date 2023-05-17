package dev.ewm.user.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.query.CheckNicknameQuery;
import dev.ewm.user.application.port.out.LoadUserPort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CheckNicknameService implements CheckNicknameQuery {

    private final LoadUserPort loadUserPort;

    @Override
    @Transactional
    public User checkNickname(String nickname) {
        return loadUserPort.findByNickname(nickname);
    }

}
