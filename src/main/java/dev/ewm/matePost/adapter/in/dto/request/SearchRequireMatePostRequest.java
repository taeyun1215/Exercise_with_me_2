package dev.ewm.matePost.adapter.in.dto.request;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class SearchRequireMatePostRequest {

    private String title;
    private String gym;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    public MatePost toEntity() {
        return MatePost.builder()
                .title(title)
                .gym(gym)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
