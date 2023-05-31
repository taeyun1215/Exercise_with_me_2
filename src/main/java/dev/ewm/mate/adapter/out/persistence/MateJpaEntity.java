package dev.ewm.mate.adapter.out.persistence;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.mate.domain.constant.Type;
import dev.ewm.matePost.adapter.out.persistence.MatePostJpaEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MateJpaEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mate_post_id")
    private Long matePostId;

}
