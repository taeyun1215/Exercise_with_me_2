package dev.ewm.matePost.application.port.out;

import dev.ewm.matePost.domain.MatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingMatePostPort {

    Page<MatePost> pageMatePostList(Pageable pageable);

}
