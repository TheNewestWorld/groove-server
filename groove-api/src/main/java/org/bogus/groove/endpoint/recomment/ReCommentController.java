package org.bogus.groove.endpoint.recomment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.recomment.ReComment;
import org.bogus.groove.domain.recomment.ReCommentService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "recomment-controller")
@RequestMapping("/api/community")
@RestController
@RequiredArgsConstructor
public class ReCommentController {
    private final ReCommentService reCommentService;

    @Operation(summary = "대댓글 작성")
    @PostMapping("/comment/{commentId}/reComment")
    public CommonResponse<Void> createReComment(@RequestBody ReCommentCreateRequest request) {
        reCommentService.createReComment(request.getContent(), request.getUserId(), request.getCommentId());
        return CommonResponse.success();
    }

    @Operation(summary = "대댓글 리스트 조회")
    @GetMapping("/comment/{commentId}/reComment")
    public CommonResponse<List<ReComment>> getReCommentList(@PathVariable Long commentId) {
        return CommonResponse.success(reCommentService.getReCommentList(commentId));
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "대댓글 수정")
    @PutMapping("/reComment/{reCommentId}")
    public CommonResponse<Void> updateReComment(
        @RequestBody ReCommentUpdateRequest request,
        @PathVariable Long reCommentId
    ) {
        reCommentService.updateReComment(reCommentId, request.getContent());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "대댓글 삭제")
    @DeleteMapping("/reComment/{reCommentId}")
    public CommonResponse<Void> deleteReComment(@PathVariable Long reCommentId) {
        reCommentService.deleteReComment(reCommentId);
        return CommonResponse.success();
    }
}
