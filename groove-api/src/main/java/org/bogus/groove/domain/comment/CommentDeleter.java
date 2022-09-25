package org.bogus.groove.domain.comment;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeleter {
    private final CommentRepository commentRepository;

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        var entity = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COMMENT));
        if (entity.getUserId() == userId) {
            entity.setDeleted(true);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
