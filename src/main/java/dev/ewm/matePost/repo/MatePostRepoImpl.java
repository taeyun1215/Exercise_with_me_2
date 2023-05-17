package dev.ewm.matePost.repo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.domain.matePost.QMatePost;
import dev.ewm.matePost.adapter.in.dto.request.SearchRequireMatePostRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class MatePostRepoImpl implements MatePostRepoCustom {

    private final JPAQueryFactory queryFactory;
    private final QMatePost matePost = QMatePost.matePost;

    @Override
    public List<MatePost> searchAll(SearchRequireMatePostRequest searchRequireMatePostRequest) {

        return queryFactory
                .selectFrom(matePost)
                .where(
                        eqTitle(searchRequireMatePostRequest.getTitle()),
                        eqGym(searchRequireMatePostRequest.getGym()),
                        betweenTime(searchRequireMatePostRequest.getStartTime(), searchRequireMatePostRequest.getEndTime())
                )
                .orderBy(matePost.createdDate.desc())
                .fetch();
    }

    // 제목 검색
    private BooleanExpression eqTitle(String searchTitle) {
        return searchTitle == null ? null : matePost.title.contains(searchTitle);
    }

    // 헬스장 이름 검색
    private BooleanExpression eqGym(String searchGym) {
        return searchGym == null ? null : matePost.gym.contains(searchGym);
    }

    // 시간 대 검색
    private BooleanExpression betweenTime(LocalTime start, LocalTime end) {

        if (start == null & end == null) {
            return null;
        } else if (end == null) {
            return matePost.startTime.goe(start);
        } else if (start == null) {
            return matePost.endTime.loe(end);
        } else {
            return matePost.startTime.between(start, end);
        }
    }

}
