package dev.ewm.user.adapter.out.persistence;

import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

     User mapToDomainEntity(UserJpaEntity userJpaEntity) {
         return new User(
                 userJpaEntity.getId(),
                 userJpaEntity.getUsername(),
                 userJpaEntity.getPassword(),
                 userJpaEntity.getNickname(),
                 userJpaEntity.getPhone(),
                 userJpaEntity.getEmail(),
                 userJpaEntity.getRole(),
                 userJpaEntity.getMatePosts()
         );
    }

    public void mapToJpaEntity() {
    }
}
