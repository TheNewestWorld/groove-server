package org.bogus.groove.domain.like;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.LikeRepository;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeDeleter {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void delete(Long userId, Long postId) {
        var entity =
            likeRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_LIKE));

        if (userId == entity.getUserId() && postId == entity.getPostId()) {
            likeRepository.delete(entity);
            PostEntity post = postRepository.getById(postId);
            post.setLikeCount(post.getLikeCount() - 1);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
