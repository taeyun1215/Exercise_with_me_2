package dev.ewm.mate;

import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.mate.domain.constant.Type;
import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mate extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToOne(targetEntity = UserJpaEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserJpaEntity user;

    @ManyToOne(targetEntity = MatePostJpaEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "matePostId")
    private MatePostJpaEntity matePost;

}
