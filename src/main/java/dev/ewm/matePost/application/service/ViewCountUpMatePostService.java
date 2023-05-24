package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.application.port.in.usecase.ViewCountUpMatePostUseCase;
import dev.ewm.matePost.application.port.out.ViewCountUpMatePostStatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class ViewCountUpMatePostService implements ViewCountUpMatePostUseCase {

    private final ViewCountUpMatePostStatePort viewCountUpMatePostStatePort;

    @Override
    public void viewCountUpMatePost(Long matePostId) {
        viewCountUpMatePostStatePort.viewCountUp(matePostId);
    }

}
