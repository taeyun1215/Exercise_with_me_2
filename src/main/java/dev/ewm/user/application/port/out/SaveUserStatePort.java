package dev.ewm.user.application.port.out;

import dev.ewm.user.domain.User;

public interface SaveUserStatePort {

    void saveUser(User user);

}
