package dev.ewm.matePost.adapter.in.dto.response;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ViewMatePostResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private String writer;
    private int view;

    public static ViewMatePostResponse from(MatePost matePost) {
        return ViewMatePostResponse.builder()
                .id(matePost.getMatePostId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .writer(matePost.getWriter())
                .view(matePost.getView())
                .build();
    }
}
