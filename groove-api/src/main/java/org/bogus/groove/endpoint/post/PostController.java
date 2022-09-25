package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.springframework.security.access.annotation.Secured;
import org.bogus.groove.domain.post.Post;
import org.bogus.groove.domain.post.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "post-controller")
@RequestMapping("/api/community/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성")
    @PostMapping
    public CommonResponse<Void> createPost(@RequestBody PostCreateRequest request) {
        postService.createPost(request.getTitle(), request.getContent(), request.getLikeCount(), request.getUserId(),
            request.getCategoryId());
        return CommonResponse.success();
    }

    @Operation(summary = "게시글 리스트 조회")
    @GetMapping
    public CommonResponse<List<PostResponse>> getPostList(Pageable pageable) {
        return CommonResponse.success(postService.getPostList(pageable));
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{postId}")
    public CommonResponse<Post> getPost(@PathVariable Long postId) {
        return CommonResponse.success(postService.getPost(postId));
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public CommonResponse<Void> updatePost(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody PostUpdateRequest request,
        @PathVariable Long postId
    ) {
        postService.updatePost(userDetails.getUserId(), postId, request.getTitle(), request.getContent(), request.getCategoryId());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public CommonResponse<Void> deletePost(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long postId) {
        postService.deletePost(userDetails.getUserId(), postId);
        return CommonResponse.success();
    }
}
