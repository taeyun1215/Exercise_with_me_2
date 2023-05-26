package dev.ewm.matePost.domain;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.mate.adapter.out.persistence.MateJpaEntity;
import dev.ewm.matePost.adapter.in.request.ModifyMatePostRequest;
import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.user.domain.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatePost extends BaseTimeEntity implements Serializable {

    private Long matePostId;
    private String title;
    private String content;
    private String gym;
    private String writer;
    private int view = 0;
    private LocalTime startTime;
    private LocalTime endTime;

    private Long userId;

    public MatePostJpaEntity toJpaEntity(User user) {
        return MatePostJpaEntity.builder()
                .id(matePostId)
                .title(title)
                .content(content)
                .gym(gym)
                .writer(writer)
                .view(view)
                .startTime(startTime)
                .endTime(endTime)
                .user(user.toJpaEntity())
                .build();
    }

    public void updateMatePost(ModifyMatePostRequest modifyMatePostRequest) {
        this.title = modifyMatePostRequest.getTitle();
        this.content = modifyMatePostRequest.getContent();
        this.gym = modifyMatePostRequest.getGym();
        this.startTime = modifyMatePostRequest.getStartTime();
        this.endTime = modifyMatePostRequest.getEndTime();
    }

}
