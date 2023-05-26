package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.adapter.in.request.CreateMatePostRequest;
import dev.ewm.matePost.application.port.in.usecase.CreateMatePostUseCase;
import dev.ewm.matePost.application.port.out.SaveMatePostPort;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMatePostService implements CreateMatePostUseCase {

    private final SaveMatePostPort saveMatePostPort;

    @Override
    public void createMatePost(CreateMatePostRequest createMatePostRequest, User user) {
        MatePost matepost = createMatePostRequest.toEntity(user);
        saveMatePostPort.saveMatePost(matepost, user);
    }

}
