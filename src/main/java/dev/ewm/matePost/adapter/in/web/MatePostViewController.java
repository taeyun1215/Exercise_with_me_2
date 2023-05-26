package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.matePost.adapter.out.response.ViewMatePostResponse;
import dev.ewm.matePost.application.port.in.query.LoadMatePostQuery;
import dev.ewm.matePost.application.port.in.usecase.ViewCountUpMatePostUseCase;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostViewController {

    private final ViewCountUpMatePostUseCase viewCountUpMatePostUseCase;
    private final LoadMatePostQuery loadMatePostQuery;

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
                viewCountUpMatePostUseCase.viewCountUpMatePost(matePostId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + matePostId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                httpServletResponse.addCookie(oldCookie);
            }
        } else {
            viewCountUpMatePostUseCase.viewCountUpMatePost(matePostId);
            Cookie newCookie = new Cookie("postView","[" + matePostId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            httpServletResponse.addCookie(newCookie);
        }
        MatePost matePost = loadMatePostQuery.loadMatePost(matePostId);
        ViewMatePostResponse response = ViewMatePostResponse.from(matePost);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
