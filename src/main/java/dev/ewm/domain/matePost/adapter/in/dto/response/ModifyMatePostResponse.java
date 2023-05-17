package dev.ewm.domain.matePost.adapter.in.dto.response;

import dev.ewm.domain.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModifyMatePostResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private User user;

    public static ModifyMatePostResponse from(MatePost matePost) {
        return ModifyMatePostResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .user(matePost.getUser())
                .build();
    }
}
