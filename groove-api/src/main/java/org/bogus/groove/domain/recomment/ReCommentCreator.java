package org.bogus.groove.domain.recomment;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.ReCommentEntity;
import org.bogus.groove.storage.repository.ReCommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReCommentCreator {
    private final ReCommentRepository reCommentRepository;

    public ReComment createReComment(String content, Long userId, Long commentId) {
        var entity = reCommentRepository.save(new ReCommentEntity(content, false, userId, commentId));
        return new ReComment(entity);
    }
}
