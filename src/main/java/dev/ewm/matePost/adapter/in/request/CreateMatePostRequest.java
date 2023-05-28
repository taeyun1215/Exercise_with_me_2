package dev.ewm.matePost.adapter.in.request;

import dev.ewm.global.common.SelfValidating;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateMatePostRequest extends SelfValidating<CreateMatePostRequest> {

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

    public CreateMatePostRequest(
            String title,
            String content,
            String gym,
            LocalTime startTime,
            LocalTime endTime
    ) {
        this.title = title;
        this.content = content;
        this.gym = gym;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

    public MatePost toEntity(User user) {
        return MatePost.builder()
                .title(title)
                .content(content)
                .gym(gym)
                .writer(user.getNickname())
                .startTime(startTime)
                .endTime(endTime)
                .userId(user.getUserId())
                .build();
    }
}
