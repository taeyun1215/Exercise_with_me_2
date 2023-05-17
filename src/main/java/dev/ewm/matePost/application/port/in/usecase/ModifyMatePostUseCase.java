package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.matePost.adapter.in.dto.request.ModifyMatePostRequest;

public interface ModifyMatePostUseCase {

    void modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId);

}
