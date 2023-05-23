package dev.ewm.matePost.domain;

import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.mate.Mate;
import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;
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
    private int view = 0;
    private LocalTime startTime;
    private LocalTime endTime;

    private Long userId;
    private List<Mate> mates;

    public MatePostJpaEntity toJpaEntity(User user) {
        return MatePostJpaEntity.builder()
                .id(matePostId)
                .title(title)
                .content(content)
                .gym(gym)
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
