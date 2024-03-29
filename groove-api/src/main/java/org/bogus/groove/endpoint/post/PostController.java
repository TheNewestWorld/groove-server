package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.attachment.PostAttachmentCreateParam;
import org.bogus.groove.domain.post.PostService;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "post-controller")
@RequestMapping("/api/community/post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping(
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CommonResponse<Void> createPost(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @RequestPart PostCreateRequest request,
        @RequestPart(required = false) List<MultipartFile> attachments
    ) {
        List<PostAttachmentCreateParam> params = new ArrayList<>();
        if (attachments != null) {
            for (MultipartFile attachment : attachments) {
                try {
                    InputStream input = attachment.getInputStream();

                    params.add(new PostAttachmentCreateParam(
                        input,
                        attachment.getOriginalFilename(),
                        attachment.getSize(),
                        extCheck(attachment.getOriginalFilename())
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        postService.createPost(request.getTitle(), request.getContent(), userDetails.getUserId(),
            request.getCategoryId(), params);
        return CommonResponse.success();
    }

    @Operation(summary = "카테고리 별 게시글 리스트 조회")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @GetMapping("/category")
    public CommonResponse<PageResponse<List<PostResponse>>> getPostList(
        @RequestParam int page,
        @RequestParam int size,
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @RequestParam SortOrderType sortOrderType,
        @RequestParam(required = false) String word,
        @RequestParam(required = false) Long categoryId) {
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
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @GetMapping("/{postId}")
    public CommonResponse<PostDetailResponse> getPost(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @PathVariable Long postId) {
        return CommonResponse.success(
            new PostDetailResponse(postService.getPost(userDetails.getUserId(), postId))
        );
    }

    @Operation(summary = "게시글 수정")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PutMapping(
        value = "/{postId}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CommonResponse<Void> updatePost(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @RequestPart PostUpdateRequest request,
        @PathVariable Long postId,
        @RequestPart(required = false) List<MultipartFile> attachments
    ) {
        List<PostAttachmentCreateParam> params = new ArrayList<>();
        if (attachments != null) {
            for (MultipartFile attachment : attachments) {
                try {
                    InputStream input = attachment.getInputStream();
                    params.add(new PostAttachmentCreateParam(
                        input,
                        attachment.getOriginalFilename(),
                        attachment.getSize(),
                        extCheck(attachment.getOriginalFilename())
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        postService.updatePost(userDetails.getUserId(), postId, request.getTitle(), request.getContent(), request.getCategoryId(), params);
        return CommonResponse.success();
    }

    @Operation(summary = "게시글 삭제")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @DeleteMapping("/{postId}")
    public CommonResponse<Void> deletePost(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @PathVariable Long postId
    ) {
        postService.deletePost(userDetails.getUserId(), postId);
        return CommonResponse.success();
    }

    private AttachmentType extCheck(String fileName) {
        AttachmentType type;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif") || ext.equals("png")) {
            type = AttachmentType.POST_IMAGE;
        } else {
            type = AttachmentType.POST_RECORD;
        }

        return type;
    }
}
