package dev.ewm.matePost.adapter.out.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mate_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatePostJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String content; // 게시글 내용

    @Column(nullable = false)
    private String gym; // 헬스장 이름

    @Column(nullable = false)
    private String writer; // 작성자

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view = 0; // 조회수

    @JsonBackReference
    @ManyToOne(targetEntity = UserJpaEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserJpaEntity user;

}
