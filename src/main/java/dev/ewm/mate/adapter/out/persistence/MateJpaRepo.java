package dev.ewm.mate.adapter.out.persistence;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MateJpaRepo extends JpaRepository<MateJpaEntity, Long> {

    Optional<MateJpaEntity> findByMatePostIdAndUserId(Long matePostId, Long userId);

//    List<MateJpaEntity> findByMatePost(MatePost matePost);

}
