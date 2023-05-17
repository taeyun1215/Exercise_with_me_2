package dev.ewm.domain.matePost.repo;

import dev.ewm.domain.matePost.domain.MatePost;
import dev.ewm.domain.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;

import java.util.List;

public interface MatePostRepoCustom {

    List<MatePost> searchAll(SearchRequireMatePostRequest searchRequireMatePostRequest);

}
