package dev.ewm.matePost.adapter.in.dto.request;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class CreateMatePostRequest {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "헬스장은 필수 입력 값입니다.")
    private String gym;

    @NotNull(message = "운동 시작하는 시간은 필수 입력 값입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @NotNull(message = "운동 끝나는 시간은 필수 입력 값입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    public MatePost toEntity(UserJpaEntity user) {
        return MatePost.builder()
                .title(title)
                .content(content)
                .gym(gym)
                .startTime(startTime)
                .endTime(endTime)
                .user(user)
                .build();
    }
}
