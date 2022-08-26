package org.bogus.groove.domain.post;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDeleter {
    private final PostRepository postRepository;

    @Transactional
    public void deletePost(Long postId) {
        var entity = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
        entity.setDeleted(true);
    }
}
