package dev.ewm.mate.adapter.out.persistence;

import dev.ewm.mate.application.port.out.DeleteMatePort;
import dev.ewm.mate.application.port.out.ExistMatePort;
import dev.ewm.mate.application.port.out.SaveMatePort;
import dev.ewm.mate.application.port.out.LoadMatePort;
import dev.ewm.mate.domain.Mate;
import dev.ewm.matePost.domain.MatePost;
import dev.ewm.user.domain.User;

public class MatePersistenceAdapter implements
        SaveMatePort, DeleteMatePort, LoadMatePort,
        ExistMatePort {

    private MateJpaRepo mateJpaRepo;
    private MatePersistenceMapper matePersistenceMapper;

    @Override
    public void saveMate(Mate mate, MatePost matePost, User user) {
        mateJpaRepo.save(matePersistenceMapper.mapToJpaEntity(mate, matePost, user));
    }

    @Override
    public void deleteMate(Long mateId) {
        mateJpaRepo.deleteById(mateId);
    }

    @Override
    public Mate loadMate(Long mateId) {
        return matePersistenceMapper.mapToDomainEntity(mateJpaRepo.findById(mateId).get());
    }

    @Override
    public Mate existMate(MatePost matePost, User user) {
        return matePersistenceMapper.mapToDomainEntity(mateJpaRepo.findByMatePostAndUser(matePost, user).get());
    }
}
