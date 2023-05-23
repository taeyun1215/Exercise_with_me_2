package dev.ewm.matePost.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.matePost.application.port.out.ModifyMatePostStatePort;
import dev.ewm.matePost.application.port.out.SaveMatePostPort;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MatePostPersistenceAdapter
        implements SaveMatePostPort, ModifyMatePostStatePort {

    private final MatePostJpaRepo matePostJpaRepo;
    private final MatePostPersistenceMapper matePostPersistenceMapper;

    @Override
    public void saveMatePost(MatePost matePost, User user) {
        matePostJpaRepo.save(matePostPersistenceMapper.mapToJpaEntity(matePost, user));
    }

    @Override
    public void modifyMatePost(MatePost matePost, User user) {
        matePostJpaRepo.save(matePostPersistenceMapper.mapToJpaEntity(matePost, user));
    }
}
