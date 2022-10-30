package org.bogus.groove.domain.like;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.storage.entity.LikeEntity;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.LikeRepository;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeCreator {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void create(Long userId, Long postId) {
        try {
            var entity = likeRepository.findByUserIdAndPostId(userId, postId);

            if (entity.isPresent()) {
                throw new InternalServerException(ErrorType.ALREADY_LIKE_POST);
            } else {
                likeRepository.save(new LikeEntity(userId, postId));
                PostEntity post = postRepository.getById(postId);
                post.setLikeCount(post.getLikeCount() + 1);
            }
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_LIKE_POST);
        }
    }
}
