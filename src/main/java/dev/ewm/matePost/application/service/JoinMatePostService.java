package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.mate.Mate;
import dev.ewm.matePost.application.port.in.usecase.JoinMatePostUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class JoinMatePostService implements JoinMatePostUseCase {

    @Override
    public List<Mate> joinMatePost(Long matePostId, User user) {
        return null;
    }

}
