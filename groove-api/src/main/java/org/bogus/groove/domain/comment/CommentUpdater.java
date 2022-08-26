package org.bogus.groove.domain.comment;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUpdater {
    private final CommentRepository commentRepository;

    @Transactional
    public void updateComment(Long commentId, String content) {
        var entity = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COMMENT));
        entity.setContent(content);
    }
}
