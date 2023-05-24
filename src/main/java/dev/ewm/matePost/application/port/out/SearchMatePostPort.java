package dev.ewm.matePost.application.port.out;

import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

import java.util.List;

public interface SearchMatePostPort {

    List<MatePost> searchAll(MatePost matePost, User user);

}
