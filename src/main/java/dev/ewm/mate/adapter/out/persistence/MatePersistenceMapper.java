package dev.ewm.mate.adapter.out.persistence;

import dev.ewm.mate.domain.Mate;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MatePersistenceMapper {

    public Mate mapToDomainEntity(MateJpaEntity mateJpaEntity) {
        return Mate.builder()
                .mateId(mateJpaEntity.getId())
                .type(mateJpaEntity.getType())
                .userId(mateJpaEntity.getUser().getId())
                .matePostId(mateJpaEntity.getMatePost().getId())
                .build();
    }

    public MateJpaEntity mapToJpaEntity(Mate mate, MatePost matePost, User user) {
        return mate.toJpaEntity(matePost, user);
    }

}
