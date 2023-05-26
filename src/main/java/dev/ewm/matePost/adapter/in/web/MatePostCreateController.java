package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.matePost.adapter.in.request.CreateMatePostRequest;
import dev.ewm.matePost.application.port.in.usecase.CreateMatePostUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostCreateController {

    private final CreateMatePostUseCase createMatePostUseCase;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> createMatePost(
            @LoginUser User user,
            @RequestBody CreateMatePostRequest createMatePostRequest
    ) {
        createMatePostUseCase.createMatePost(createMatePostRequest, user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("생성이 완료 되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
