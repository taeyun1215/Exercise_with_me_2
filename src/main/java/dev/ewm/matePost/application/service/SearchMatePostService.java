package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.matePost.application.port.in.query.SearchMatePostQuery;
import dev.ewm.matePost.application.port.out.SearchMatePostPort;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class SearchMatePostService implements SearchMatePostQuery {

    private final SearchMatePostPort searchMatePostPort;

    @Override
    public List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest, User user) {
        MatePost matePost = searchRequireMatePostRequest.toEntity();
        List<MatePost> matePosts = searchMatePostPort.searchAll(matePost, user);

        return matePosts;
    }

}
