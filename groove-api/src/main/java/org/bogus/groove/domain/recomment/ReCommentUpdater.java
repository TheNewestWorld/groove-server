package org.bogus.groove.domain.recomment;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.ReCommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReCommentUpdater {
    private final ReCommentRepository reCommentRepository;

    @Transactional
    public void updateReComment(Long reCommentId, String content) {
        var entity = reCommentRepository.findById(reCommentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_RECOMMENT));
        entity.setContent(content);
    }
}
