package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.mate.Mate;
import dev.ewm.user.domain.User;

import java.util.List;

public interface JoinMateUseCase {

    // TODO : MateController에 넣어야하는지 생각하기
    void joinMate(Long matePostId, User user);

}
