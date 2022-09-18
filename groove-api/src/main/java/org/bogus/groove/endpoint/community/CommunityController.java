package org.bogus.groove.endpoint.community;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.community.CommunityService;
import org.bogus.groove.domain.community.Post;
import org.springframework.security.access.annotation.Secured;
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

    @PostMapping("/api/community/post")
    public CommonResponse<Void> createPost(@RequestBody PostCreateRequest request) {
        communityService.createPost(request.getTitle(), request.getContent(), request.getLikeCount(), request.getUserId(),
            request.getCategoryId());
        return CommonResponse.success();
    }

    @GetMapping("/api/community/post/{postId}")
    public CommonResponse<Post> getPost(@PathVariable Long postId) {
        return CommonResponse.success(communityService.getPost(postId));
    }

    @Secured(SecurityCode.USER)
    @PutMapping("/api/community/post/{postId}")
    public CommonResponse<Void> updatePost(
        @RequestBody PostUpdateRequest request,
        @PathVariable Long postId
    ) {
        communityService.updatePost(postId, request.getTitle(), request.getContent(), request.getCategoryId());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @DeleteMapping("/api/community/{postId}")
    public CommonResponse<Void> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return CommonResponse.success();
    }
}
