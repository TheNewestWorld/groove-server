package org.bogus.groove.endpoint.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
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
    private final CommentService commentService;

    @Operation(summary = "댓글 작성 (parentId, postId가 동일하면 댓글 다르면 대댓글)")
    @PostMapping("/post/{postId}/comment")
    public CommonResponse<Void> createComment(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CommentCreateRequest request) {
        commentService.createComment(request.getContent(), request.getParentId(), userDetails.getUserId(), request.getPostId());
        return CommonResponse.success();
    }

    @Operation(summary = "댓글 리스트 조회")
    @GetMapping("/post/{postId}/comment")
    public CommonResponse<List<CommentResponse>> getCommentList(@PathVariable Long postId) {
        return CommonResponse.success(commentService.getCommentList(postId).stream().map(comment -> new CommentResponse(comment)).collect(
            Collectors.toList()));
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "댓글 수정")
    @PutMapping("/comment/{contentId}")
    public CommonResponse<Void> updateComment(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody CommentUpdateRequest request,
        @PathVariable Long contentId
    ) {
        commentService.updateComment(userDetails.getUserId(), contentId, request.getContent());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comment/{contentId}")
    public CommonResponse<Void> deleteComment(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long contentId) {
        commentService.deleteComment(userDetails.getUserId(), contentId);
        return CommonResponse.success();
    }
}
