package org.bogus.groove.domain.comment;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {
    private final CommentRepository commentRepository;

    public List<Comment> readAllComment(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(
                entity -> new Comment(entity.getId(), entity.getContent(), entity.isDeleted(), entity.getUserId(), entity.getPostId()))
            .collect(Collectors.toList());
    }
}
