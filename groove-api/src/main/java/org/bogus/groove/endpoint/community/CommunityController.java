package org.bogus.groove.endpoint.community;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.domain.community.CommunityService;
import org.bogus.groove.domain.community.Post;
import org.bogus.groove.endpoint.middleware.Authorized;
import org.bogus.groove.endpoint.something.SomethingUpdateRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/api/community")
    public CommonResponse<Void> create(@RequestBody PostCreateRequest request) {
        communityService.create(request.getTitle(), request.getContent(), request.getLikeCount(), request.isTemporary(),
            request.getUserId(), request.getCategoryId());
        return CommonResponse.success();
    }

    @GetMapping("/api/community/{postId}")
    public CommonResponse<Post> get(@PathVariable Long postId) {
        return CommonResponse.success(communityService.get(postId));
    }

    @Authorized({Authority.USER})
    @PutMapping("/api/community/{postId}")
    public CommonResponse<Void> update(
        @RequestBody PostUpdateRequest request,
        @PathVariable Long postId
    ) {
        communityService.updatePost(postId, request.getTitle(), request.getContent(), request.isTemporary(), request.getCategoryId());
        return CommonResponse.success();
    }

    @Authorized({Authority.USER})
    @DeleteMapping("/api/community/{postId}")
    public CommonResponse<Void> delete(@PathVariable Long postId) {
        communityService.delete(postId);
        return CommonResponse.success();
    }
}
