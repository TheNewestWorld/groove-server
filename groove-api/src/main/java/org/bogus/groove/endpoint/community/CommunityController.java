package org.bogus.groove.endpoint.community;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.domain.community.CommunityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/api/community")
    public CommonResponse<Void> createPost(@RequestBody PostCreateRequest request) {
        communityService.createPost(request.getTitle(), request.getContent(), request.getLikeCount(), request.isTemporary(),
            request.getUserId(), request.getCategoryId());
        return CommonResponse.success();
    }
}
