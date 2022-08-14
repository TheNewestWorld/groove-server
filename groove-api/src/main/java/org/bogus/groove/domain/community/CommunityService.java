package org.bogus.groove.domain.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityCreator communityCreator;
    private final CommunityReader communityReader;
    private final CommunityUpdater communityUpdater;
    private final CommunityDeleter communityDeleter;

    public Post create(String title, String content, Integer likeCount, boolean isTemporary, Long userId, Long categoryId) {
        return communityCreator.create(title, content, likeCount, isTemporary, userId, categoryId);
    }

    public Post get(Long postId) {
        return communityReader.read(postId);
    }

    public void updatePost(Long postId, String title, String content, boolean isTemporary, Long categoryId) {
        communityUpdater.updatePost(postId, title, content, isTemporary, categoryId);
    }

    public void delete(Long postId) {
        communityDeleter.delete(postId);
    }
}
