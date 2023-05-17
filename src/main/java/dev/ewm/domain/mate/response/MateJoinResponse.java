package dev.ewm.domain.mate.response;

import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.mate.domain.constant.Type;
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

    public static List<MateJoinResponse> from(List<Mate> mates) {
        List<MateJoinResponse> mateJoinResponses = new ArrayList<>();

        for (Mate mate : mates) {
            MateJoinResponse mateJoinResponse = MateJoinResponse.builder()
                    .id(mate.getId())
                    .username(mate.getUser().getUsername())
                    .type(mate.getType())
                    .build();

            mateJoinResponses.add(mateJoinResponse);
        }

        return mateJoinResponses;
    }
}
