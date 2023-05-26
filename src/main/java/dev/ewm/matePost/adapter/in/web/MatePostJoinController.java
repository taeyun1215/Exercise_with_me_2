package dev.ewm.matePost.adapter.in.web;

import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.mate.application.port.in.JoinMateUseCase;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostJoinController {

    private final JoinMateUseCase joinMateUseCase;

    @GetMapping("/join/{matePostId}")
    public ResponseEntity<ReturnObject> joinMate(
            @LoginUser User user,
            @PathVariable("matePostId") Long matePostId
    ) {
        joinMateUseCase.joinMate(matePostId, user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }


}
