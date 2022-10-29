package org.bogus.groove.domain.post;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {

    private final PostRepository postRepository;

    public Slice<Post> readAllPosts(Long categoryId, int page, int size, SortOrderType sortOrderType, String word) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrderType.getCol()).descending());
        return postRepository.findAllPosts(categoryId, pageable, word)
            .map(Post::new);
    }

    public Post readPost(Long postId) {
        return readOrNull(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
    }

    private Optional<Post> readOrNull(Long postId) {
        return postRepository.findByIdAndIsDeletedFalse(postId).map(
            Post::new);
    }
}
