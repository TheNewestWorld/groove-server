package org.bogus.groove.endpoint.like;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.like.LikeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "like-controller")
@RequestMapping("/api/community/post")
@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 좋아요")
    @PostMapping("/{postId}/like")
    public CommonResponse<Void> like(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long postId) {
        likeService.like(userDetails.getUserId(), postId);
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 좋아요 취소")
    @DeleteMapping("/{postId}/like")
    public CommonResponse<Void> unLike(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long postId) {
        likeService.unLike(userDetails.getUserId(), postId);
        return CommonResponse.success();
    }
}
