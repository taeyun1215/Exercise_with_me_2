package dev.ewm.matePost.adapter.in.dto.response;

import dev.ewm.matePost.domain.MatePost;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PagingMatePostResponse {

    private Long id;
    private String title;
    private String writer;
    private String gym;
    private int view;
    private int mateCount;

    public static List<PagingMatePostResponse> from(Page<MatePost> matePosts) {
        List<PagingMatePostResponse> pagingMatePostResponses = new ArrayList<>();

        for (MatePost matePost : matePosts) {
            PagingMatePostResponse pagingMatePostResponse = PagingMatePostResponse.builder()
                    .id(matePost.getMatePostId())
                    .title(matePost.getTitle())
                    .writer(matePost.getWriter())
                    .gym(matePost.getGym())
                    .view(matePost.getView())
                    .mateCount(matePost.getMates() == null ? 0 : matePost.getMates().size())
                    .build();

            pagingMatePostResponses.add(pagingMatePostResponse);
        }

        return pagingMatePostResponses;
    }

}
