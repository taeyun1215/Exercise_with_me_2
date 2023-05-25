package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.matePost.application.port.in.query.LoadMatePostQuery;
import dev.ewm.matePost.application.port.in.usecase.ModifyMatePostUseCase;
import dev.ewm.matePost.application.port.out.ModifyMatePostStatePort;
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
public class ModifyMatePostService implements ModifyMatePostUseCase {

    private final LoadMatePostQuery loadMatePostQuery;
    private final SaveMatePostPort saveMatePostPort;

    @Override
    public void modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId, User user) {
        MatePost matePost = loadMatePostQuery.loadMatePost(matePostId);
        matePost.updateMatePost(modifyMatePostRequest);
        saveMatePostPort.saveMatePost(matePost, user);
    }

}
