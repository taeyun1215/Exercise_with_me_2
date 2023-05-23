package dev.ewm.matePost.application.port.out;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

public interface ModifyMatePostStatePort {

    void modifyMatePost(MatePost matePost, User user);

}
