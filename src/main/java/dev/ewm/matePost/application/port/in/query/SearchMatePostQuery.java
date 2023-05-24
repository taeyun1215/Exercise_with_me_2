package dev.ewm.matePost.application.port.in.query;

import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

import java.util.List;

public interface SearchMatePostQuery {

    List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest, User user);

}
