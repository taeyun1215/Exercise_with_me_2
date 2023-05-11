package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatePostModifyResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private User user;

    public static MatePostModifyResponse from(MatePost matePost) {
        return MatePostModifyResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .user(matePost.getUser())
                .build();
    }
}
