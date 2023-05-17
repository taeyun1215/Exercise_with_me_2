package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.application.port.in.query.PagingMatePostQuery;
import dev.ewm.matePost.domain.MatePost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class PagingMatePostService implements PagingMatePostQuery {

    private final PagingMatePostQuery pagingMatePostQuery;

    @Override
    public Page<MatePost> pageMatePostList(Pageable pageable) {
        return null;
    }

}
