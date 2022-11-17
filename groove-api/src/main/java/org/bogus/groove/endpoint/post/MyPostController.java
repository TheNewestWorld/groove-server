package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.post.PostService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPostController {
    private final PostService postService;

    @Operation(summary = "마이페이지 - 작성한 게시물")
    @Secured(SecurityCode.USER)
    @GetMapping("/api/users/self/posts")
    public CommonResponse<PageResponse<List<MyPostGetResponse>>> getMyPosts(
        @RequestParam int page,
        @RequestParam int size,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        var result = postService.getMyPosts(userDetails.getUserId(), page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.getContent().stream().map(MyPostGetResponse::new).collect(Collectors.toList()),
                result.hasNext()
            )
        );
    }

    @Operation(summary = "마이페이지 - 좋아한 게시물")
    @Secured(SecurityCode.USER)
    @GetMapping("/api/users/self/liked-posts")
    public CommonResponse<PageResponse<List<MyPostGetResponse>>> getLikedPosts(
        @RequestParam int page,
        @RequestParam int size,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        var result = postService.getLikedPosts(userDetails.getUserId(), page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.getContent().stream().map(MyPostGetResponse::new).collect(Collectors.toList()),
                result.hasNext()
            )
        );
    }
}
