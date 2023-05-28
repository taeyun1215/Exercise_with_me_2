package dev.ewm.user.adapter.out.persistence;

import javax.persistence.*;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.mate.adapter.out.persistence.MateJpaEntity;
import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.user.domain.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(
            targetEntity = MatePostJpaEntity.class, mappedBy = "user",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    private List<MatePostJpaEntity> matePosts;

}
