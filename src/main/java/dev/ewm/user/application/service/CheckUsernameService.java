package dev.ewm.user.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.query.CheckUsernameQuery;
import dev.ewm.user.application.port.out.LoadUserPort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CheckUsernameService implements CheckUsernameQuery {

    private final LoadUserPort loadUserPort;

    @Override
    @Transactional
    public User checkUsername(String username) {
        return loadUserPort.findByUsername(username);
    }

}
