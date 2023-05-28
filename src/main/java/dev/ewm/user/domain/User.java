package dev.ewm.user.domain;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import dev.ewm.user.domain.constant.Role;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity implements Serializable {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Role role;

    private List<Long> matePostIds;

    public UserJpaEntity toJpaEntity() {
        return UserJpaEntity.builder()
                .id(userId)
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .role(role)
                .build();
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void addMatePosts(Long matePostId) {
        matePostIds.add(matePostId);
    }

}