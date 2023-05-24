package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.utils.ReturnObject;
import dev.ewm.matePost.adapter.in.dto.response.PagingMatePostResponse;
import dev.ewm.matePost.application.port.in.query.PagingMatePostQuery;
import dev.ewm.matePost.domain.MatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostPagingController {

    private final PagingMatePostQuery pagingMatePostQuery;

    @GetMapping("/all")
    public ResponseEntity<ReturnObject> pagingMatePost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MatePost> matePosts = pagingMatePostQuery.pageMatePostList(pageable);
        List<PagingMatePostResponse> response = PagingMatePostResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
