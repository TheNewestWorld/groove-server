package org.bogus.groove.endpoint.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.notice.NoticeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "notice-contoller")
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 작성")
    @Secured(SecurityCode.ADMIN)
    @PostMapping("/api/notice")
    public CommonResponse<Void> createNotice(@RequestBody NoticeCreateRequest request) {
        noticeService.createNotice(request.getTitle(), request.getContent());
        return CommonResponse.success();
    }

    @Operation(summary = "공지사항 수정")
    @Secured(SecurityCode.ADMIN)
    @PutMapping("/api/notice/{noticeId}")
    public CommonResponse<Void> updateNotice(@RequestBody NoticeUpdateRequest request,
                                             @PathVariable Long noticeId) {
        noticeService.updateNotice(noticeId, request.getTitle(), request.getContent());
        return CommonResponse.success();

    }

    @Operation(summary = "공지사항 삭제")
    @Secured(SecurityCode.ADMIN)
    @DeleteMapping("/api/notice/{noticeId}")
    public CommonResponse<Void> deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return CommonResponse.success();
    }

    @Operation(summary = "공지사항 리스트 조회")
    @GetMapping("/api/notice")
    public CommonResponse<PageResponse<List<NoticeGetResponse>>> getNoticeList(@RequestParam int page,
                                                                    @RequestParam int size) {
        var result = noticeService.getNoticeList(page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.getContent().stream().map(NoticeGetResponse::new).collect(Collectors.toList()),
                result.hasNext()
            )
        );
    }

    @Operation(summary = "공지사항 리스트 조회")
    @GetMapping("/api/notice/{noticeId}")
    public CommonResponse<NoticeGetResponse> getNotice(@PathVariable Long noticeId) {
        return CommonResponse.success(new NoticeGetResponse(noticeService.getNotice(noticeId)));
    }
}
