package org.bogus.groove.endpoint.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.domain.comment.Comment;
import org.bogus.groove.domain.comment.CommentService;
import org.bogus.groove.endpoint.middleware.Authorized;
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

    @Operation(summary = "댓글 수정")
    @Authorized({Authority.USER})
    @PutMapping("/comment/{contentId}")
    public CommonResponse<Void> updateComment(
        @RequestBody CommentUpdateRequest request,
        @PathVariable Long contentId
    ) {
        communityService.updateComment(contentId, request.getContent());
        return CommonResponse.success();
    }

    @Operation(summary = "댓글 삭제")
    @Authorized({Authority.USER})
    @DeleteMapping("/comment/{contentId}")
    public CommonResponse<Void> deleteComment(@PathVariable Long contentId) {
        communityService.deleteComment(contentId);
        return CommonResponse.success();
    }
}
