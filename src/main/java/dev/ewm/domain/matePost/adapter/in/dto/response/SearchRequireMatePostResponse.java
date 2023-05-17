package dev.ewm.domain.matePost.adapter.in.dto.response;

import dev.ewm.domain.matePost.domain.MatePost;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchRequireMatePostResponse {

    private List<MatePost> matePosts;

    public static SearchRequireMatePostResponse from(List<MatePost> matePosts) {
        return SearchRequireMatePostResponse.builder()
                .matePosts(matePosts)
                .build();
    }

}
