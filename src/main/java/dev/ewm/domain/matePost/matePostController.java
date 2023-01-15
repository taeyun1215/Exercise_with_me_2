package dev.ewm.domain.matePost;

import dev.ewm.domain.matePost.request.matePostCreateRequest;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.response.UserRegisterResponse;
import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class matePostController {

    private final matePostService matePostService;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> createMatePost(
            @LoginUser String username,
            @Validated @RequestBody matePostCreateRequest matePostCreateRequest
    ) {
//        User user = userService.registerUser(userRegisterRequest);
//        UserRegisterResponse response = UserRegisterResponse.from(user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(username)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
