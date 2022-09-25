package org.bogus.groove.domain.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public List<Post> readAllPost(Pageable pageable) {
        return postRepository.findAllByIsDeletedFalseOrderByCreatedAtDesc(pageable).stream().map(
            entity -> new Post(entity)).collect(Collectors.toList());
    }

    public Post readPost(Long postId) {
        return readOrNull(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
    }

    public Optional<Post> readOrNull(Long postId) {
        return postRepository.findById(postId).map(
            entity -> new Post(entity));
    }

}
