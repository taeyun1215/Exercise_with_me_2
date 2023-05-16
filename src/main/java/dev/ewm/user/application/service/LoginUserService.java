package dev.ewm.user.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.adapter.in.request.LoginUserRequest;
import dev.ewm.user.application.port.in.query.CheckUsernameQuery;
import dev.ewm.user.application.port.in.usecase.LoginUserUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class LoginUserService implements LoginUserUseCase {

    private final CheckUsernameQuery checkUsernameQuery;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User loginUser(LoginUserRequest loginUserRequest) {
        User findUser = checkUsernameQuery.checkUsername(loginUserRequest.getUsername());

        if (passwordEncoder.matches(loginUserRequest.getPassword(), findUser.getPassword())) {
            log.info("로그인한 아이디 : ", findUser.getUsername());
            return findUser;
        }
        else throw new EntityNotFoundException("일치하는 정보가 없습니다.");

    }

}
