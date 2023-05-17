package dev.ewm.user.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.user.application.port.out.LoadUserPort;
import dev.ewm.user.application.port.out.SaveUserPort;
import dev.ewm.user.application.port.out.UpdateUserStatePort;
import dev.ewm.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter
        implements SaveUserPort, LoadUserPort, UpdateUserStatePort {

    private final UserRepo userRepo;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepo.save(userPersistenceMapper.mapToJpaEntity(user));
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userPersistenceMapper.mapToDomainEntity(userRepo.findByUsername(username).orElseThrow(
                () -> null
        ));
    }

    @Override
    @Transactional
    public User findByNickname(String nickname) {
        return userPersistenceMapper.mapToDomainEntity(userRepo.findByNickname(nickname).orElseThrow(
                () -> null
        ));
    }

    @Override
    @Transactional
    public void updateUsername(User user, String username) {
        UserJpaEntity findUserJpaEntity = userRepo.findById(user.getId()).orElseThrow(
                EntityNotFoundException::new
        );

        User saveUser = userPersistenceMapper.mapToDomainEntity(findUserJpaEntity);
        saveUser.updateUsername(username);

        userRepo.save(userPersistenceMapper.mapToJpaEntity(saveUser));
    }

}

