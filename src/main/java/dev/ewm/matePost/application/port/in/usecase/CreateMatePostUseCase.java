package dev.ewm.matePost.application.port.in.usecase;

import dev.ewm.matePost.adapter.in.dto.request.CreateMatePostRequest;

public interface CreateMatePostUseCase {

    void createMatePost(CreateMatePostRequest createMatePostRequest);

}
