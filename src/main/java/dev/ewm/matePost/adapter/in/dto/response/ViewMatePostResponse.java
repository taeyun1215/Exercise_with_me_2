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
    private int view;
    private User user;

    public static ViewMatePostResponse from(MatePost matePost) {
        return ViewMatePostResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .view(matePost.getView())
                .user(matePost.getUser())
                .build();
    }
}
