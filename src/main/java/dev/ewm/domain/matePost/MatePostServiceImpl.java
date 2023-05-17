package dev.ewm.domain.matePost;

import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.mate.MateRepo;
import dev.ewm.domain.mate.domain.constant.Type;
import dev.ewm.domain.matePost.domain.MatePost;
import dev.ewm.domain.matePost.repo.MatePostRepo;
import dev.ewm.domain.matePost.adapter.in.dto.request.CreateMatePostRequest;
import dev.ewm.domain.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.domain.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MatePostServiceImpl implements MatePostService {

    private final MatePostRepo matePostRepo;
    private final MateRepo mateRepo;

    @Override
    @Transactional
    public Page<MatePost> pageMatePostList(Pageable pageable) {
        return matePostRepo.findAll(pageable);
    }

    @Override
    @Transactional
    public List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest) {
        return matePostRepo.searchAll(searchRequireMatePostRequest);
    }

    @Override
    @Transactional
    public MatePost viewDetailMatePost(Long matePostId) {
        return matePostRepo.findById(matePostId).get();
    }

    @Override
    @Transactional
    public void viewCountUpMatePost(Long matePostId) {
        matePostRepo.viewCountUp(matePostId);
    }

    @Override
    @Transactional
    public MatePost createMatePost(CreateMatePostRequest createMatePostRequest, User user) {
        MatePost matePost = createMatePostRequest.toEntity(user);
        matePostRepo.save(matePost);
        log.info("운동 메이트 게시글이 추가됐습니다.");

        Mate mate = Mate.builder()
                .type(Type.LEADER)
                .user(user)
                .matePost(matePost)
                .build();

        mateRepo.save(mate);
        log.info("운동 메이트 그룹이 생성됐습니다.");

        return matePost;
    }

    @Override
    @Transactional
    public List<Mate> joinMate(Long matePostId, User user) {
        MatePost matePost = matePostRepo.findById(matePostId)
                .orElseThrow(EntityNotFoundException::new);

        mateRepo.findByMatePostAndUser(matePost, user).ifPresentOrElse(
                existMate -> {
                    mateRepo.delete(existMate);
                    log.info("운동 그룹에서 삭제됐습니다.");
                },
                () -> {
                    Mate joinMate = Mate.builder()
                            .type(Type.PARTICIPANT)
                            .user(user)
                            .matePost(matePost)
                            .build();

                    mateRepo.save(joinMate);
                    log.info("운동 그룹에 등록됐습니다.");
                }
        );

        return mateRepo.findByMatePost(matePost);
    }

    @Override
    @Transactional
    public MatePost modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId) {
        MatePost matePost = matePostRepo.findById(matePostId)
                .orElseThrow(EntityNotFoundException::new);

        matePost.updateMatePost(modifyMatePostRequest);
        return matePostRepo.save(matePost);
    }

}
