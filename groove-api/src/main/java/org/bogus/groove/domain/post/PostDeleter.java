package org.bogus.groove.domain.post;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.storage.entity.CommentEntity;
import org.bogus.groove.storage.repository.CommentRepository;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDeleter {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void deletePost(Long userId, Long postId) {
        var entity = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
        if (entity.getUserId() == userId) {
            entity.setDeleted(true);
            List<CommentEntity> comments = commentRepository.findAllByPostIdAndIsDeletedFalse(postId);
            for (CommentEntity comment : comments) {
                comment.setDeleted(true);
            }
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
