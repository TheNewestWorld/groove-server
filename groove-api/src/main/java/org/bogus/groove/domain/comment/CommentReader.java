package org.bogus.groove.domain.comment;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.post.PostReader;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {
    private final CommentRepository commentRepository;
    private final PostReader postReader;

    public List<Comment> readAllPostComment(Long postId) {
        checkPostIsExist(postId);
        return commentRepository.findAllByPostIdAndIsDeletedFalse(postId).stream().filter(entity -> entity.getId() == entity.getParentId())
            .map(
                Comment::new).collect(Collectors.toList());
    }

    public List<Comment> readAllPostReComment(Long commentId) {
        return commentRepository.findAllByParentIdAndIsDeletedFalse(commentId).stream()
            .filter(entity -> entity.getId() != entity.getParentId())
            .map(Comment::new).collect(Collectors.toList());
    }

    public Integer countPostComment(Long postId) {
        return commentRepository.countByPostIdAndIsDeletedFalse(postId);
    }

    private void checkPostIsExist(Long postId) {
        postReader.readPost(postId);
    }
}
