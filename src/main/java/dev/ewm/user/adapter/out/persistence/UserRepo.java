package dev.ewm.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUsername(String username);
    Optional<UserJpaEntity> findByNickname(String nickname);
    Optional<UserJpaEntity> findByEmail(String email);

}
