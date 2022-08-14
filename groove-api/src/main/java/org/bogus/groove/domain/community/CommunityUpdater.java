package org.bogus.groove.domain.community;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.error.ErrorType;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityUpdater {
    private final PostRepository postRepository;

    @Transactional
    public void updatePost(Long postId, String title, String content, boolean isTemporary, Long categoryId) {
        var entity = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_POST));
        entity.setTitle(title);
        entity.setContent(content);
        entity.setTemporary(isTemporary);
        entity.setCategoryId(categoryId);
    }
}
