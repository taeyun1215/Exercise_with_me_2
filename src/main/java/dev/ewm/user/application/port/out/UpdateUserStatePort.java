package dev.ewm.user.application.port.out;

import dev.ewm.user.domain.User;

public interface UpdateUserStatePort {

    void updateUsername(User user, String username);

}
