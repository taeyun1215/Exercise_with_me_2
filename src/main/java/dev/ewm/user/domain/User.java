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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatePost> matePosts = new ArrayList<>();

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
}