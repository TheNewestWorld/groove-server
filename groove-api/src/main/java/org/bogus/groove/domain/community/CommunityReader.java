package org.bogus.groove.domain.community;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.error.ErrorType;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityReader {
    private final PostRepository postRepository;

    public Post read(Long postId) {
        return readOrNull(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
    }

    public Optional<Post> readOrNull(Long postId) {
        return postRepository.findById(postId).map(
            entity -> new Post(entity.getId(), entity.getTitle(), entity.getContent(), entity.getLikeCount(), entity.isTemporary(),
                entity.getUserId(),
                entity.getCategoryId()));
    }
}
