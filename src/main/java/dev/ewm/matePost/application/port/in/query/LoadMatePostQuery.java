package dev.ewm.matePost.application.port.in.query;

import dev.ewm.matePost.domain.MatePost;

public interface LoadMatePostQuery {

    MatePost loadMatePost(Long matePostId);

}
