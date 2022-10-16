package org.bogus.groove.domain.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public List<Post> readAllPosts(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAllByIsDeletedFalseOrderByCreatedAtDesc(pageable);
        return posts.stream().map(
            entity -> new Post(entity)).collect(Collectors.toList());
    }

    public List<Post> readAllPosts(Long categoryId, Pageable pageable) {
        Page<PostEntity> posts = postRepository.findByCategoryIdAndIsDeletedFalseOrderByCreatedAtDesc(categoryId, pageable);
        return posts.stream().map(
            entity -> new Post(entity)).collect(Collectors.toList());
    }

    public Post readPost(Long postId) {
        return readOrNull(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
    }

    private Optional<Post> readOrNull(Long postId) {
        return postRepository.findByIdAndIsDeletedFalse(postId).map(
            entity -> new Post(entity));
    }
}
