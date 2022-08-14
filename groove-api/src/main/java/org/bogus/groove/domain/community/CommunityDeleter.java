package org.bogus.groove.domain.community;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityDeleter {
    private final PostRepository postRepository;

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
