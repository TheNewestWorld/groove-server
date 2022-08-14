package org.bogus.groove.domain.community;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityCreator {
    private final PostRepository postRepository;

    public Post createPost(String title, String content, Integer likeCount, boolean isTemporary, Long userId, Long categoryId){
        var entity = postRepository.save(new PostEntity(title, content, likeCount, isTemporary, userId, categoryId));
        return new Post(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getLikeCount(),
            entity.isTemporary(),
            entity.getUserId(),
            entity.getCategoryId()
        );
    }
}
