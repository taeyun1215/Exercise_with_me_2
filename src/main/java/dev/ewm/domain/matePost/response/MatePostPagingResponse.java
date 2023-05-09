package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class MatePostPagingResponse {

    private Long id;
    private String title;
    private String username;
    private String gym;
    private int view;
    private int mateCount;

    public static List<MatePostPagingResponse> from(Page<MatePost> matePosts) {
        List<MatePostPagingResponse> matePostPagingResponses = new ArrayList<>();

        for (MatePost matePost : matePosts) {
            MatePostPagingResponse matePostPagingResponse = MatePostPagingResponse.builder()
                    .id(matePost.getId())
                    .title(matePost.getTitle())
                    .username(matePost.getUser().getUsername())
                    .gym(matePost.getGym())
                    .view(matePost.getView())
                    .mateCount(matePost.getMateList() == null ? 0 : matePost.getMateList().size())
                    .build();

            matePostPagingResponses.add(matePostPagingResponse);
        }

        return matePostPagingResponses;
    }

}
