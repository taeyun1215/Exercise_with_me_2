package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.matePost.adapter.in.request.SearchRequireMatePostRequest;
import dev.ewm.matePost.adapter.out.response.SearchRequireMatePostResponse;
import dev.ewm.matePost.application.port.in.query.SearchMatePostQuery;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostSearchController {

    private final SearchMatePostQuery searchMatePostQuery;

    @PostMapping("/search")
    public ResponseEntity<ReturnObject> searchMatePost(
            @LoginUser User user,
            @RequestBody SearchRequireMatePostRequest searchRequireMatePostRequest
    ) {
        List<MatePost> matePosts = searchMatePostQuery.searchMatePostList(searchRequireMatePostRequest, user);

        SearchRequireMatePostResponse response = SearchRequireMatePostResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
