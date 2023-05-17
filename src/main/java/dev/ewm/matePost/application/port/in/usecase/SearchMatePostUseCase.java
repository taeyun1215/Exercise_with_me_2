package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.matePost.domain.MatePost;

import java.util.List;

public interface SearchMatePostUseCase {

    List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest);

}
