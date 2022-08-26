package org.bogus.groove.domain.recomment;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReCommentService {
    private final ReCommentCreator reCommentCreator;
    private final ReCommentReader reCommentReader;
    private final ReCommentUpdater reCommentUpdater;
    private final ReCommentDeleter reCommentDeleter;

    public ReComment createReComment(String content, Long userId, Long commentId) {
        return reCommentCreator.createReComment(content, userId, commentId);
    }

    public List<ReComment> getReCommentList(Long commentId) {
        return reCommentReader.readAllReComment(commentId);
    }

    public void updateReComment(Long reCommentId, String content) {
        reCommentUpdater.updateReComment(reCommentId, content);
    }

    public void deleteReComment(Long reCommentId) {
        reCommentDeleter.deleteReComment(reCommentId);
    }
}
