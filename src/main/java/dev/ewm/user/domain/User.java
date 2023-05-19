package dev.ewm.user.domain;

import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import dev.ewm.user.domain.constant.Role;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
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

    private List<MatePost> matePosts;
//    private List<Mate> mates = new ArrayList<>();


    public UserJpaEntity toJpaEntity() {
        // matePosts 필드 매핑
        List<MatePostJpaEntity> matePostJpaEntities = new ArrayList<>();
        for (MatePost matePost : matePosts) {
            matePostJpaEntities.add(matePost.toJpaEntity());
        }

        return UserJpaEntity.builder()
                .id(userId)
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .role(role)
                .matePosts(matePostJpaEntities)
                .build();
    }

    public void addPost(MatePost matePost) {
        matePosts.add(matePost);
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}