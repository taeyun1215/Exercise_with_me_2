package dev.ewm.user.adapter.out.persistence;

import dev.ewm.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

     User mapToDomainEntity(UserJpaEntity userJpaEntity) {
         return User.builder()
                 .username(userJpaEntity.getUsername())
                 .password(userJpaEntity.getPassword())
                 .nickname(userJpaEntity.getNickname())
                 .phone(userJpaEntity.getPhone())
                 .email(userJpaEntity.getEmail())
                 .role(userJpaEntity.getRole())
                 .build();
    }

    public UserJpaEntity mapToJpaEntity(User user) {

         return null;
    }
    
}
