package dev.ewm.user.application.port.in.usecase;

import dev.ewm.user.domain.User;

public interface CheckNicknameUseCase {

    User checkNickname(String nickname);

}
