package dev.ewm.domain.matePost;

import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.matePost.adapter.in.dto.request.CreateMatePostRequest;
import dev.ewm.domain.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.domain.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import dev.ewm.domain.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface MatePostService {

    // 운동 메이트 게시글 페이징
    Page<MatePost> pageMatePostList(Pageable pageable);

    // 운동 메이트 게시글 검색
    List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest);

    // 운동 메이스 게시글 상세 조회, 조회수 추가(session)
    MatePost viewDetailMatePost(Long matePostId);

    // 운동 메이스 게시글 조회수 추가(session)
    void viewCountUpMatePost(Long matePostId);

    // DB 운동 메이트 게시글 저장
    MatePost createMatePost(CreateMatePostRequest createMatePostRequest, User user);

    // 운동 메이트 조인
    List<Mate> joinMate(Long matePostId, User user);

    // 운동 메이트 게시글 수정
    MatePost modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId);

}
