package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.matePost.adapter.in.dto.response.ModifyMatePostResponse;
import dev.ewm.matePost.application.port.in.usecase.ModifyMatePostUseCase;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostModifyController {

    private final ModifyMatePostUseCase modifyMatePostUseCase;

    @PutMapping("/modify/{matePostId}")
    public ResponseEntity<ReturnObject> modifyMatePost(
            @LoginUser User user,
            @PathVariable("matePostId") Long matePostId,
            @RequestBody ModifyMatePostRequest modifyMatePostRequest
    ) {
        modifyMatePostUseCase.modifyMatePost(modifyMatePostRequest, matePostId, user);
        ModifyMatePostResponse response = ModifyMatePostResponse.from(matePost);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }


}
