package dev.ewm.mate.application.port.out;

import dev.ewm.mate.domain.Mate;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

public interface SaveMatePort {

    void saveMate(Mate mate, MatePost matePost, User user);

}
