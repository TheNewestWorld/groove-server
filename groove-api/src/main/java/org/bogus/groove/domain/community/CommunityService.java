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

    public Post createPost(String title, String content, Integer likeCount, Long userId, Long categoryId) {
        return communityCreator.createPost(title, content, likeCount, userId, categoryId);
    }

    public Post getPost(Long postId) {
        return communityReader.readPost(postId);
    }

    public void updatePost(Long postId, String title, String content, Long categoryId) {
        communityUpdater.updatePost(postId, title, content, categoryId);
    }

    public void deletePost(Long postId) {
        communityDeleter.deletePost(postId);
    }
}
