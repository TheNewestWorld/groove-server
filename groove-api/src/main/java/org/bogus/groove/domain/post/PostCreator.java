package org.bogus.groove.domain.post;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreator {
    private final PostRepository postRepository;

    public Post createPost(String title, String content, Integer likeCount, Long userId, Long categoryId) {
        var entity = postRepository.save(new PostEntity(title, content, likeCount, false, userId, categoryId));
        return new Post(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getLikeCount(),
            entity.isDeleted(),
            entity.getUserId(),
            entity.getCategoryId()
        );
    }
}
