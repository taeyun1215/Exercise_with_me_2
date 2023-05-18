package dev.ewm.user.domain;

import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.constant.Role;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    private UserId userId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Role role;

    private List<MatePost> matePosts;
//    private List<Mate> mates = new ArrayList<>();

    @Value
    public static class UserId{
        Long value;
    }

    public static User loadUser(
            Long id,
            String username,
            String password,
            String nickname,
            String phone,
            String email,
            Role role,
            List<MatePost> matePosts
    ) {
        return new User(id, username, password, nickname, phone, email, role, matePosts);
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}