package org.bogus.groove.domain.like;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove.storage.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeDeleter {
    private final LikeRepository likeRepository;

    public void delete(Long userId, Long postId) {
        var entity =
            likeRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_LIKE));

        if (userId == entity.getUserId() && postId == entity.getPostId()) {
            likeRepository.delete(entity);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
