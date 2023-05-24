package dev.ewm.matePost.adapter.out.persistence;

import java.util.List;

public interface MatePostJpaRepoCustom {

    List<MatePostJpaEntity> searchAll(MatePostJpaEntity matePostJpaEntity);

}
