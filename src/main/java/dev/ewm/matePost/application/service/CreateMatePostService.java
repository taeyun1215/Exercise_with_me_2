package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.adapter.in.dto.request.CreateMatePostRequest;
import dev.ewm.matePost.application.port.in.usecase.CreateMatePostUseCase;
import dev.ewm.matePost.application.port.out.SaveMatePostStatePort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMatePostService implements CreateMatePostUseCase {

    private final SaveMatePostStatePort saveMatePostStatePort;

    @Override
    public void createMatePost(CreateMatePostRequest createMatePostRequest, User user) {
        saveMatePostStatePort.saveMatePost();
    }

}
