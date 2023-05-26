package dev.ewm.mate.domain;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.mate.adapter.out.persistence.MateJpaEntity;
import dev.ewm.mate.domain.constant.Type;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mate extends BaseTimeEntity implements Serializable {

    private Long mateId;
    private Type type;

    private Long userId;
    private Long matePostId;

    public MateJpaEntity toJpaEntity(MatePost matePost, User user) {
        return MateJpaEntity.builder()
                .id(mateId)
                .type(type)
                .user(user.toJpaEntity())
                .matePost(matePost.toJpaEntity(user))
                .build();
    }

}
