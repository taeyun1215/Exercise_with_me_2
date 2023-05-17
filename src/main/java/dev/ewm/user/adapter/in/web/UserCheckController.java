package dev.ewm.user.adapter.in.web;

import dev.ewm.global.error.ErrorCode;
import dev.ewm.global.utils.ReturnObject;
import dev.ewm.user.application.port.in.query.CheckNicknameQuery;
import dev.ewm.user.application.port.in.query.CheckUsernameQuery;
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
@RequestMapping("/users")
public class UserCheckController {

    private final CheckUsernameQuery checkUsernameQuery;
    private final CheckNicknameQuery checkNicknameQuery;

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<ReturnObject> checkUsername(
            @PathVariable("username") String username
    ) {
        User user = checkUsernameQuery.checkUsername(username);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(username)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

    @GetMapping("/nickname/{nickname}/exists")
    public ResponseEntity<ReturnObject> checkNickname(
            @PathVariable("nickname") String nickname
    ) {
        User user = checkNicknameQuery.checkNickname(nickname);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(nickname)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

}
