package dev.ewm.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.domain.matePost.MatePost;
import dev.ewm.user.domain.constant.Role;
import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Role role;
    private List<MatePost> matePosts;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user")
//    private List<Mate> mates = new ArrayList<>();

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