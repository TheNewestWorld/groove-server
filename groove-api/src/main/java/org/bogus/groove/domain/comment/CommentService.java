package org.bogus.groove.domain.comment;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCreator commentCreator;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;

    public Comment createComment(String content, Long userId, Long postId) {
        return commentCreator.createComment(content, userId, postId);
    }

    public List<Comment> getCommentList(Long postId) {
        return commentReader.readAllComment(postId);
    }

    public void updateComment(Long userId, Long commentId, String content) {
        commentUpdater.updateComment(userId, commentId, content);
    }

    public void deleteComment(Long userId, Long commentId) {
        commentDeleter.deleteComment(userId, commentId);
    }
}
