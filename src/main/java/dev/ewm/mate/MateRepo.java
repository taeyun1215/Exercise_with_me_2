package dev.ewm.mate;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateRepo extends JpaRepository<Mate, Long> {

    Optional<Mate> findByMatePostAndUser(MatePost matePost, User user);

    List<Mate> findByMatePost(MatePost matePost);

}
