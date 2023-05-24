package dev.ewm.matePost.application.port.out;

import dev.ewm.matePost.domain.MatePost;

public interface LoadMatePostPort {

    MatePost loadMatePost(Long matePostId);

}
