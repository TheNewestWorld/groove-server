package org.bogus.groove.domain.comment;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.CommentEntity;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreator {
    private final CommentRepository commentRepository;

    public Comment createComment(String content, Long userId, Long postId) {
        var entity = commentRepository.save(new CommentEntity(content, false, userId, postId));
        return new Comment(entity);
    }
}
