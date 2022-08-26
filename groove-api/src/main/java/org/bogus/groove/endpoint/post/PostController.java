package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.SecurityCode;
import org.springframework.security.access.annotation.Secured;
import org.bogus.groove.domain.post.Post;
import org.bogus.groove.domain.post.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CommonResponse<List<Post>> getPostList() {
        return CommonResponse.success(postService.getPostList());
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{postId}")
    public CommonResponse<Post> getPost(@PathVariable Long postId) {
        return CommonResponse.success(postService.getPost(postId));
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 수정")
    public CommonResponse<Void> updatePost(
        @RequestBody PostUpdateRequest request,
        @PathVariable Long postId
    ) {
        postService.updatePost(postId, request.getTitle(), request.getContent(), request.getCategoryId());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.USER)
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public CommonResponse<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return CommonResponse.success();
    }
}
