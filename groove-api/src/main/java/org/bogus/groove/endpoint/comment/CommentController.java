package org.bogus.groove.endpoint.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.comment.Comment;
import org.bogus.groove.domain.comment.CommentService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "comment-controller")
@RequestMapping("/api/community")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService communityService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/post/{postId}/comment")
    public CommonResponse<Void> createComment(@RequestBody CommentCreateRequest request) {
        communityService.createComment(request.getContent(), request.getUserId(), request.getPostId());
        return CommonResponse.success();
    }

    @Operation(summary = "댓글 리스트 조회")
    @GetMapping("/post/{postId}/comment")
    public CommonResponse<List<Comment>> getCommentList(@PathVariable Long postId) {
        return CommonResponse.success(communityService.getCommentList(postId));
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "댓글 수정")
    @PutMapping("/comment/{contentId}")
    public CommonResponse<Void> updateComment(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody CommentUpdateRequest request,
        @PathVariable Long contentId
    ) {
        communityService.updateComment(userDetails.getUserId(), contentId, request.getContent());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comment/{contentId}")
    public CommonResponse<Void> deleteComment(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long contentId) {
        communityService.deleteComment(userDetails.getUserId(), contentId);
        return CommonResponse.success();
    }
}