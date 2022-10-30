package org.bogus.groove.domain.comment;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.CommentRepository;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeleter {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        var entity = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COMMENT));
        if (entity.getUserId() == userId) {
            entity.setDeleted(true);
            PostEntity post = postRepository.getById(entity.getPostId());
            post.setCommentCount(post.getCommentCount() - 1);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
