package dev.ewm.user.application.port.in.command;

import dev.ewm.global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginUserCommand extends SelfValidating<LoginUserCommand> {

    @NotBlank(message = "아이디은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    public LoginUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
        this.validateSelf();
    }
}
