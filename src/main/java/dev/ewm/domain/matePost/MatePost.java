package dev.ewm.domain.matePost;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.ewm.domain.user.User;
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
public class MatePost {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String content; // 게시글 내용

    @Column(nullable = false)
    private String gym; // 헬스장 이름

    @Column(nullable = false)
    private String startTime; // 운동 시작 시간

    @Column(nullable = false)
    private String endTime; // 운동 끝나는 시간

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view = 0; // 조회수

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

}