package dev.ewm.matePost;

import dev.ewm.mate.Mate;
import dev.ewm.mate.response.MateJoinResponse;
import dev.ewm.matePost.adapter.in.dto.request.CreateMatePostRequest;
import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.domain.matePost.adapter.in.dto.response.*;
import dev.ewm.matePost.adapter.in.dto.response.*;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostController {

    private final MatePostService matePostService;

    @GetMapping("/all")
    public ResponseEntity<ReturnObject> pagingMatePost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MatePost> matePosts = matePostService.pageMatePostList(pageable);
        List<PagingMatePostResponse> response = PagingMatePostResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @PostMapping("/search")
    public ResponseEntity<ReturnObject> searchMatePost(
            @LoginUser User user,
            @RequestBody SearchRequireMatePostRequest searchRequireMatePostRequest
    ) {
        List<MatePost> matePosts = matePostService.searchMatePostList(searchRequireMatePostRequest);

        SearchRequireMatePostResponse response = SearchRequireMatePostResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @GetMapping("/view/{matePostId}")
    public ResponseEntity<ReturnObject> viewMatePost(
            @LoginUser User user,
            @PathVariable("matePostId") Long matePostId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        Cookie oldCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + matePostId.toString() + "]")) {
                matePostService.viewCountUpMatePost(matePostId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + matePostId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                httpServletResponse.addCookie(oldCookie);
            }
        } else {
            matePostService.viewCountUpMatePost(matePostId);
            Cookie newCookie = new Cookie("postView","[" + matePostId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            httpServletResponse.addCookie(newCookie);
        }
        MatePost matePost = matePostService.viewDetailMatePost(matePostId);
        ViewMatePostResponse response = ViewMatePostResponse.from(matePost);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> createMatePost(
            @LoginUser User user,
            @Validated @RequestBody CreateMatePostRequest createMatePostRequest
    ) {
        MatePost matePost = matePostService.createMatePost(createMatePostRequest, user);
        CreateMatePostResponse response = CreateMatePostResponse.from(matePost);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @GetMapping("/join/{matePostId}")
    public ResponseEntity<ReturnObject> joinMate(
            @LoginUser User user,
            @PathVariable("matePostId") Long matePostId
    ) {
        List<Mate> mates = matePostService.joinMate(matePostId, user);
        List<MateJoinResponse> response = MateJoinResponse.from(mates);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }


}
