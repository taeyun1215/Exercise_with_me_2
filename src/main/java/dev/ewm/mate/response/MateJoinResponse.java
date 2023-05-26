package dev.ewm.mate.response;

import dev.ewm.mate.adapter.out.persistence.MateJpaEntity;
import dev.ewm.mate.domain.constant.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
public class MateJoinResponse {

    private Long id;
    private String username;
    private Type type;

    public static List<MateJoinResponse> from(List<MateJpaEntity> mateJpaEntities) {
        List<MateJoinResponse> mateJoinResponses = new ArrayList<>();

        for (MateJpaEntity mateJpaEntity : mateJpaEntities) {
            MateJoinResponse mateJoinResponse = MateJoinResponse.builder()
                    .id(mateJpaEntity.getId())
                    .username("")
                    .type(mateJpaEntity.getType())
                    .build();

            mateJoinResponses.add(mateJoinResponse);
        }

        return mateJoinResponses;
    }
}
