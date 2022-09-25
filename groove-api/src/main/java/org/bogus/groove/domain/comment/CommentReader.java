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

    public List<Comment> readAllComment(Long postId) {
        checkPostIsExist(postId);
        return commentRepository.findAllByPostId(postId).stream().map(
                entity -> new Comment(entity)).collect(Collectors.toList());
    }

    public Integer countPostComment(Long postId) {
        return commentRepository.countByPostId(postId);
    }

    private void checkPostIsExist(Long postId) {
        postReader.readPost(postId);
    }
}
