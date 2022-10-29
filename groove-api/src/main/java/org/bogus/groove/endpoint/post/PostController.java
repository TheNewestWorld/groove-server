package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.post.PostService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "post-controller")
@RequestMapping("/api/community/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성")
    @PostMapping
    public CommonResponse<Void> createPost(@AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody PostCreateRequest request) {
        postService.createPost(request.getTitle(), request.getContent(), userDetails.getUserId(),
            request.getCategoryId());
        return CommonResponse.success();
    }

    @Operation(summary = "카테고리 별 게시글 리스트 조회")
    @GetMapping("/category/{categoryId}")
    public CommonResponse<PageResponse<List<PostResponse>>> getPostList(
        @RequestParam int page,
        @RequestParam int size,
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam SortOrderType sortOrderType,
        @RequestParam String word,
        @PathVariable Long categoryId) {
        var result = postService.getPostList(userDetails.getUserId(), categoryId, page, size, sortOrderType, word);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.map(PostResponse::new).toList(),
                result.hasNext()
            )
        );
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{postId}")
    public CommonResponse<PostDetailResponse> getPost(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                      @PathVariable Long postId) {
        return CommonResponse.success(new PostDetailResponse(postService.getPost(userDetails.getUserId(), postId)));
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
