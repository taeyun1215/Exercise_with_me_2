package dev.ewm.mate.adapter.out.persistence;

import dev.ewm.mate.adapter.out.persistence.MateJpaEntity;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MateJpaRepo extends JpaRepository<MateJpaEntity, Long> {

    Optional<MateJpaEntity> findByMatePostAndUser(MatePost matePost, User user);

    List<MateJpaEntity> findByMatePost(MatePost matePost);

}
