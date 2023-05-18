package dev.ewm.matePost.application.port.out;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

public interface SaveMatePostStatePort {

    void saveMatePost(MatePost matePost, User user);

}
