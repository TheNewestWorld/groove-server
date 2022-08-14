package org.bogus.groove.domain.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityCreator communityCreator;

    public Post createPost(String title, String content, Integer likeCount, boolean isTemporary, Long userId, Long categoryId){
        return communityCreator.createPost(title, content, likeCount, isTemporary, userId, categoryId);
    }
}
