package dev.ewm.matePost.adapter.in.dto.response;

import dev.ewm.matePost.domain.MatePost;
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
                .id(matePost.getMatePostId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .user(matePost.getUser())
                .build();
    }
}
