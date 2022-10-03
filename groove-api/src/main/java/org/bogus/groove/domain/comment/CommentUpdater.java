package org.bogus.groove.domain.comment;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUpdater {
    private final CommentRepository commentRepository;

    @Transactional
    public void updateComment(Long userId, Long commentId, String content) {
        var entity = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COMMENT));
        if (entity.getUserId() == userId) {
            entity.setContent(content);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
