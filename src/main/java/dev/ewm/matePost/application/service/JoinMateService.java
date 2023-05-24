package dev.ewm.matePost.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.mate.Mate;
import dev.ewm.mate.domain.constant.Type;
import dev.ewm.matePost.application.port.in.usecase.JoinMateUseCase;
import dev.ewm.matePost.application.port.out.LoadMatePostPort;
import dev.ewm.matePost.application.port.out.SaveMatePort;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class JoinMateService implements JoinMateUseCase {

    private final SaveMatePort saveMatePort;
    private final LoadMatePostPort loadMatePostPort;

    @Override
    public void joinMate(Long matePostId, User user) {
//        MatePost matePost = loadMatePostPort.loadMatePost(matePostId);
//
//        mateRepo.findByMatePostAndUser(matePost, user).ifPresentOrElse(
//                existMate -> {
//                    mateRepo.delete(existMate);
//                    log.info("운동 그룹에서 삭제됐습니다.");
//                },
//                () -> {
//                    Mate joinMate = Mate.builder()
//                            .type(Type.PARTICIPANT)
//                            .user(user)
//                            .matePost(matePost)
//                            .build();
//
//                    mateRepo.save(joinMate);
//                    log.info("운동 그룹에 등록됐습니다.");
//                }
//        );
//
//        mateRepo.findByMatePost(matePost);

    }

}
