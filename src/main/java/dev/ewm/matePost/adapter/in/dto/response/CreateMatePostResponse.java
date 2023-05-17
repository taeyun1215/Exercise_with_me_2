package dev.ewm.matePost.adapter.in.dto.response;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;


@Getter
@Builder
public class CreateMatePostResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private LocalTime startTime;
    private LocalTime endTime;
    private User user;

    public static CreateMatePostResponse from(MatePost matePost) {
        return CreateMatePostResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .startTime(matePost.getStartTime())
                .endTime(matePost.getEndTime())
                .user(matePost.getUser())
                .build();
    }
}
