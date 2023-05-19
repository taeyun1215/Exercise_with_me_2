package dev.ewm.matePost.adapter.out.persistence;

import dev.ewm.matePost.domain.MatePost;
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
                .build();
    }


    public MatePostJpaEntity mapToJpaEntity(MatePost matePost) {
        return matePost.toJpaEntity();
    }

}
