package dev.ewm.matePost.application.port.out;

import dev.ewm.user.domain.User;

public interface SaveMatePort {

    void saveMate(Long matePostId, User user);

}
