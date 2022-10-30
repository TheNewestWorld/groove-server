package org.bogus.groove.domain.comment;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCreator commentCreator;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;
    private final UserReader userReader;

    public Comment createComment(String content, Long parentId, Long userId, Long postId) {
        return commentCreator.createComment(content, parentId, userId, postId);
    }

    public List<CommentGetResult> getCommentList(Long postId) {
        return commentReader.readAllPostComment(postId).stream()
            .map(comment -> new CommentGetResult(comment, userReader.read(comment.getUserId()).getNickname(),
                commentReader.readAllPostReComment(
                        comment.getId()).stream()
                    .map(reComment -> new CommentGetResult(reComment, userReader.read(reComment.getUserId()).getNickname())).collect(
                        Collectors.toList()))).collect(
                Collectors.toList());
    }

    public void updateComment(Long userId, Long commentId, String content) {
        commentUpdater.updateComment(userId, commentId, content);
    }

    public void deleteComment(Long userId, Long commentId) {
        commentDeleter.deleteComment(userId, commentId);
    }
}
