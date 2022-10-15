package org.bogus.groove.domain.comment;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.storage.entity.CommentEntity;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreator {
    private final CommentRepository commentRepository;

    public Comment createComment(String content, Long parentId, Long userId, Long postId) {
        try {
            var entity = commentRepository.save(new CommentEntity(content, false, parentId, userId, postId));
            return new Comment(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CREATE_COMMENT);
        }
    }
}
