package dev.ewm.user.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.user.adapter.in.dto.request.RegisterUserRequest;
import dev.ewm.user.application.port.in.usecase.RegisterUserUseCase;
import dev.ewm.user.application.port.out.SaveUserPort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(RegisterUserRequest registerUserCommand) {
        if (!Objects.equals(registerUserCommand.getPassword(), registerUserCommand.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        User saveUser = registerUserCommand.toEntity(passwordEncoder);
        saveUserPort.saveUser(saveUser);

        return saveUser;
    }

}
