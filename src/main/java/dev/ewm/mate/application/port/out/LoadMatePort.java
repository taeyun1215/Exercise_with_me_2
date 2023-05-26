package dev.ewm.mate.application.port.out;

import dev.ewm.mate.domain.Mate;

public interface LoadMatePort {

    Mate loadMate(Long mateId);

}
