package org.bogus.groove.domain.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeCreator likeCreator;
    private final LikeDeleter likeDeleter;

    public void like(Long userId, Long postId) {
        likeCreator.create(userId, postId);
    }

    public void unLike(Long userId, Long postId) {
        likeDeleter.delete(userId, postId);
    }
}
