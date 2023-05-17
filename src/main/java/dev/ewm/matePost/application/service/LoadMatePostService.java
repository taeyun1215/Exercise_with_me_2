package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.application.port.in.query.LoadMatePostQuery;
import dev.ewm.matePost.application.port.out.LoadMatePostPort;
import dev.ewm.matePost.domain.MatePost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class LoadMatePostService implements LoadMatePostQuery {

    private final LoadMatePostPort loadMatePostPort;

    @Override
    public MatePost loadMatePost(Long matePostId) {
        return null;
    }

}
