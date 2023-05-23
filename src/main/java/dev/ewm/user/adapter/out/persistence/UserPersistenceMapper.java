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

        return User.builder()
                 .userId(userJpaEntity.getId())
                 .username(userJpaEntity.getUsername())
                 .password(userJpaEntity.getPassword())
                 .nickname(userJpaEntity.getNickname())
                 .phone(userJpaEntity.getPhone())
                 .email(userJpaEntity.getEmail())
                 .role(userJpaEntity.getRole())
                 .build();
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return user.toJpaEntity();
    }

}
