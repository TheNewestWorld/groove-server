package org.bogus.groove.domain.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public List<Post> readAllPost(Pageable pageable) {
        Page<PostEntity> posts = readLatestPosts(pageable);
        return posts.stream().map(
            entity -> new Post(entity)).collect(Collectors.toList());
    }

    private Page<PostEntity> readLatestPosts(Pageable pageable) {
        return postRepository.findAllByIsDeletedFalseOrderByCreatedAtDesc(pageable);
    }

    public Post readPost(Long postId) {
        return readOrNull(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
    }

    private Optional<Post> readOrNull(Long postId) {
        return postRepository.findById(postId).map(
            entity -> new Post(entity));
    }
}
