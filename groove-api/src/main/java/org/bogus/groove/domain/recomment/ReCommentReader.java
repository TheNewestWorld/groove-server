package org.bogus.groove.domain.recomment;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.ReCommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReCommentReader {
    private final ReCommentRepository reCommentRepository;

    public List<ReComment> readAllReComment(Long commentId) {
        return reCommentRepository.findAllByCommentId(commentId).stream().map(
                entity -> new ReComment(entity)).collect(Collectors.toList());
    }
}
