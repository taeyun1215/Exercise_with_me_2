package dev.ewm.matePost.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.matePost.application.port.out.*;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class MatePostPersistenceAdapter
        implements LoadMatePostPort, SaveMatePostPort, ModifyMatePostStatePort,
        SearchMatePostPort, ViewCountUpMatePostStatePort, PagingMatePostPort,
        SaveMatePort {

    private final MatePostJpaRepo matePostJpaRepo;
    private final MatePostPersistenceMapper matePostPersistenceMapper;

    @Override
    public MatePost loadMatePost(Long matePostId) {
        return matePostPersistenceMapper.mapToDomainEntity(matePostJpaRepo.findById(matePostId).get());
    }

    @Override
    public void saveMatePost(MatePost matePost, User user) {
        matePostJpaRepo.save(matePostPersistenceMapper.mapToJpaEntity(matePost, user));
    }

    @Override
    public void modifyMatePost(MatePost matePost, User user) {
        matePostJpaRepo.save(matePostPersistenceMapper.mapToJpaEntity(matePost, user));
    }

    @Override
    public List<MatePost> searchAll(MatePost matePost, User user) {
        List<MatePostJpaEntity> matePostJpaEntities = matePostJpaRepo.searchAll(matePostPersistenceMapper.mapToJpaEntity(matePost, user));
        return matePostPersistenceMapper.mapToDomainEntities(matePostJpaEntities);
    }

    @Override
    public void viewCountUp(Long matePostId) {
        matePostJpaRepo.viewCountUp(matePostId);
    }

    @Override
    public Page<MatePost> pageMatePostList(Pageable pageable) {
        Page<MatePostJpaEntity> matePostJpaEntityPage = matePostJpaRepo.findAll(pageable);
        List<MatePost> matePosts = matePostPersistenceMapper.mapToDomainEntities(matePostJpaEntityPage.getContent());
        return new PageImpl<>(matePosts, pageable, matePostJpaEntityPage.getTotalElements());
    }

    @Override
    public void saveMate(Long matePostId, User user) {

    }


}
