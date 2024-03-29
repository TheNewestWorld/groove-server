package org.bogus.groove.endpoint.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.GrooveUserDetails;
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

    @Operation(summary = "댓글 작성 (댓글은 parentId가 0, 대댓글은 parentId가 댓글 id)")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping("/post/{postId}/comment")
    public CommonResponse<Void> createComment(@AuthenticationPrincipal GrooveUserDetails userDetails,
                                              @PathVariable Long postId,
                                              @RequestBody CommentCreateRequest request) throws IOException {
        commentService.createComment(request.getContent(), request.getParentId(), userDetails.getUserId(), postId);
        return CommonResponse.success();
    }

    @Operation(summary = "댓글 리스트 조회")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @GetMapping("/post/{postId}/comment")
    public CommonResponse<List<CommentResponse>> getCommentList(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @PathVariable Long postId) {
        return CommonResponse.success(
            commentService.getCommentList(userDetails.getUserId(), postId).stream().map(CommentResponse::new).collect(
                Collectors.toList()));
    }

    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @Operation(summary = "댓글 수정")
    @PutMapping("/comment/{commentId}")
    public CommonResponse<Void> updateComment(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @RequestBody CommentUpdateRequest request,
        @PathVariable Long commentId
    ) {
        commentService.updateComment(userDetails.getUserId(), commentId, request.getContent());
        return CommonResponse.success();
    }

    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comment/{commentId}")
    public CommonResponse<Void> deleteComment(@AuthenticationPrincipal GrooveUserDetails userDetails, @PathVariable Long commentId) {
        commentService.deleteComment(userDetails.getUserId(), commentId);
        return CommonResponse.success();
    }
}
