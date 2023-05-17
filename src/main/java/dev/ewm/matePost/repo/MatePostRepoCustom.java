package dev.ewm.matePost.repo;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;

import java.util.List;

public interface MatePostRepoCustom {

    List<MatePost> searchAll(SearchRequireMatePostRequest searchRequireMatePostRequest);

}
