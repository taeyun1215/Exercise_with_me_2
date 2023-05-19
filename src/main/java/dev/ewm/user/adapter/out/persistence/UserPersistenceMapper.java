package dev.ewm.user.adapter.out.persistence;

import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserPersistenceMapper {

    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        List<MatePost> matePosts = mapMatePostJpaEntitiesToDomainEntities(userJpaEntity.getMatePosts());

        return User.builder()
                .userId(userJpaEntity.getId())
                 .username(userJpaEntity.getUsername())
                 .password(userJpaEntity.getPassword())
                 .nickname(userJpaEntity.getNickname())
                 .phone(userJpaEntity.getPhone())
                 .email(userJpaEntity.getEmail())
                 .role(userJpaEntity.getRole())
                 .matePosts(matePosts)
                 .build();
    }

    private List<MatePost> mapMatePostJpaEntitiesToDomainEntities(List<MatePostJpaEntity> matePostJpaEntities) {
        List<MatePost> matePosts = new ArrayList<>();
        for (MatePostJpaEntity matePostJpaEntity : matePostJpaEntities) {
            MatePost matePost = MatePost.builder()
                    .matePostId(matePostJpaEntity.getId())
                    .title(matePostJpaEntity.getTitle())
                    .content(matePostJpaEntity.getContent())
                    .gym(matePostJpaEntity.getGym())
                    .view(matePostJpaEntity.getView())
                    .startTime(matePostJpaEntity.getStartTime())
                    .endTime(matePostJpaEntity.getEndTime())
                    .build();

            matePosts.add(matePost);
        }
        return matePosts;
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return user.toJpaEntity();
    }

}
