package dev.ewm.matePost.application.port.in.query;

import dev.ewm.matePost.domain.MatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingMatePostQuery {

    Page<MatePost> pageMatePostList(Pageable pageable);

}
