package dev.ewm.user.application.service;

import dev.ewm.domain.user.UserRepo;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.application.port.in.usecase.RegisterUserUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        if (!Objects.equals(userRegisterRequest.getPassword(), userRegisterRequest.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        User user = userRegisterRequest.toEntity(passwordEncoder);
        userRepo.save(user);

        log.info("회원가입한 아이디 : ", user.getUsername());
        return user;
    }

}
