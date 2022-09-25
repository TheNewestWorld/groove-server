package org.bogus.groove.domain.post;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.InternalServerException;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreator {
    private final PostRepository postRepository;

    public Post createPost(String title, String content, Integer likeCount, Long userId, Long categoryId) {
        try {
            var entity = postRepository.save(new PostEntity(title, content, likeCount, false, userId, categoryId));
            return new Post(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CREATE_POST);
        }
    }
}