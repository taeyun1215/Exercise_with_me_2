package dev.ewm.user.application.service;

import dev.ewm.domain.user.UserRepo;
import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.usecase.loginUserUseCase;
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
public class loginUserService implements loginUserUseCase {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User loginUser(UserLoginRequest userLoginRequest) {
        User findUser = userRepo.findByUsername(userLoginRequest.getUsername());

        if (passwordEncoder.matches(userLoginRequest.getPassword(), findUser.getPassword())) {
            log.info("로그인한 아이디 : ", findUser.getUsername());
            return findUser;
        }
        else throw new EntityNotFoundException("일치하는 정보가 없습니다.");

    }

}
