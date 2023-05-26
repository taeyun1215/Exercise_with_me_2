package dev.ewm.mate.application.service;

import dev.ewm.global.annotation.UseCase;
import dev.ewm.mate.application.port.in.JoinMateUseCase;
import dev.ewm.mate.application.port.out.DeleteMatePort;
import dev.ewm.mate.application.port.out.ExistMatePort;
import dev.ewm.mate.domain.Mate;
import dev.ewm.mate.domain.constant.Type;
import dev.ewm.matePost.application.port.out.LoadMatePostPort;
import dev.ewm.mate.application.port.out.SaveMatePort;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class JoinMateService implements JoinMateUseCase {

    private final SaveMatePort saveMatePort;
    private final ExistMatePort existMatePort;
    private final DeleteMatePort deleteMatePort;
    private final LoadMatePostPort loadMatePostPort;

    @Override
    public void joinMate(Long matePostId, User user) {
        MatePost matePost = loadMatePostPort.loadMatePost(matePostId);
        Optional<Mate> mate = Optional.ofNullable(existMatePort.existMate(matePost, user));

        mate.ifPresentOrElse(
                existMate -> {
                    deleteMatePort.deleteMate(existMate.getMateId());
                    log.info("운동 그룹에서 삭제됐습니다.");
                },
                () -> {
                    Mate joinMate = Mate.builder()
                            .type(Type.PARTICIPANT)
                            .userId(user.getUserId())
                            .matePostId(matePostId)
                            .build();

                    saveMatePort.saveMate(joinMate, matePost, user);
                    log.info("운동 그룹에 등록됐습니다.");
                }
        );
    }
}
