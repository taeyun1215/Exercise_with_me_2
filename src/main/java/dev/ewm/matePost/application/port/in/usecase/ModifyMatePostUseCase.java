package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;
import dev.ewm.user.domain.User;

public interface ModifyMatePostUseCase {

    void modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId, User user);

}
