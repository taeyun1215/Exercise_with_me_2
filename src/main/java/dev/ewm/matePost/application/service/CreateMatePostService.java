package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.adapter.in.dto.request.CreateMatePostRequest;
import dev.ewm.matePost.application.port.in.usecase.CreateMatePostUseCase;
import dev.ewm.user.application.port.out.SaveUserStatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMatePostService implements CreateMatePostUseCase {

    private final SaveUserStatePort saveUserStatePort;

    @Override
    public void createMatePost(CreateMatePostRequest createMatePostRequest) {

    }

}
