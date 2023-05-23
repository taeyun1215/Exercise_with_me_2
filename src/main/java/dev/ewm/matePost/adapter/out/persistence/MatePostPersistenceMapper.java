package dev.ewm.matePost.adapter.out.persistence;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MatePostPersistenceMapper {

    public MatePost mapToDomainEntity(MatePostJpaEntity matePostJpaEntity) {
        return MatePost.builder()
                .matePostId(matePostJpaEntity.getId())
                .title(matePostJpaEntity.getTitle())
                .content(matePostJpaEntity.getContent())
                .gym(matePostJpaEntity.getGym())
                .view(matePostJpaEntity.getView())
                .startTime(matePostJpaEntity.getStartTime())
                .endTime(matePostJpaEntity.getEndTime())
                .userId(matePostJpaEntity.getUser().getId())
                .build();
    }

    public MatePostJpaEntity mapToJpaEntity(MatePost matePost, User user) {
        return matePost.toJpaEntity(user);
    }

}
