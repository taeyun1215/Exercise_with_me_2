package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.matePost.adapter.in.request.CreateMatePostRequest;
import dev.ewm.user.domain.User;

public interface CreateMatePostUseCase {

    void createMatePost(CreateMatePostRequest createMatePostRequest, User user);

}
