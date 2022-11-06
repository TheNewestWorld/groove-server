package org.bogus.groove.domain.comment;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.domain.user.UserReader;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCreator commentCreator;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;
    private final UserReader userReader;
    private final AttachmentReader attachmentReader;

    public Comment createComment(String content, Long parentId, Long userId, Long postId) {
        return commentCreator.createComment(content, parentId, userId, postId);
    }

    public List<CommentGetResult> getCommentList(Long postId) {
        return commentReader.readAllPostComment(postId).stream()
            .map(comment -> new CommentGetResult(
                comment,
                userReader.read(comment.getUserId()).getNickname(),
                getProfileUri(comment.getUserId()),
                commentReader.readAllPostReComment(
                    comment.getId()).stream().map(reComment -> new CommentGetResult(
                    reComment,
                    userReader.read(reComment.getUserId()).getNickname(),
                    getProfileUri(reComment.getUserId()))
                ).collect(Collectors.toList()))
            ).collect(Collectors.toList());
    }

    public void updateComment(Long userId, Long commentId, String content) {
        commentUpdater.updateComment(userId, commentId, content);
    }

    public void deleteComment(Long userId, Long commentId) {
        commentDeleter.deleteComment(userId, commentId);
    }

    private String getProfileUri(Long userId) {
        return attachmentReader.readAll(userId, AttachmentType.PROFILE)
            .stream().findFirst().map(Attachment::getUri).orElse(null);
    }
}
