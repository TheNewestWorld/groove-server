package org.bogus.groove.domain.post;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostRepository postRepository;

    @Transactional
    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId) {
        var entity = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
        if (entity.getUserId() == userId) {
            entity.setTitle(title);
            entity.setContent(content);
            entity.setCategoryId(categoryId);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
