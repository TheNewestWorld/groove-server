package org.bogus.groove.endpoint.inquiry;

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.attachment.InquiryAttachmentCreateParam;
import org.bogus.groove.domain.inquiry.InquiryService;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @Operation(summary = "문의사항 작성")
    @Secured({SecurityCode.USER, SecurityCode.ADMIN, SecurityCode.TRAINER})
    @PostMapping(path = "/api/inquiry",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<Void> createInquiry(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                              @RequestPart InquiryCreateRequest request,
                                              @RequestPart(required = false) List<MultipartFile> attachments) {
        List<InquiryAttachmentCreateParam> attachmentCreateParamList = new ArrayList<>();
        if (attachments != null) {
            for (MultipartFile attachment : attachments) {
                try {
                    attachmentCreateParamList.add(new InquiryAttachmentCreateParam(
                        attachment.getInputStream(),
                        attachment.getOriginalFilename(),
                        attachment.getSize(),
                        AttachmentType.INQUIRY_IMAGE)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        inquiryService.create(grooveUserDetails.getUserId(), request.getTitle(), request.getContent(), attachmentCreateParamList);
        return CommonResponse.success();
    }


    @Operation(summary = "문의사항 조회")
    @Secured({SecurityCode.USER, SecurityCode.ADMIN, SecurityCode.TRAINER})
    @GetMapping("/api/inquiry/{inquiryId}")
    public CommonResponse<InquiryGetResponse> getInquiry(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                                         @PathVariable Long inquiryId) {
        var inquiryGetResult = inquiryService.get(inquiryId);
        InquiryGetResponse inquiryGetResponse;

        if (inquiryGetResult.getUserId() == grooveUserDetails.getUserId()) {
            inquiryGetResponse = new InquiryGetResponse(inquiryGetResult);
        } else {
            throw new NotFoundException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        return CommonResponse.success(inquiryGetResponse);
    }

    @Operation(summary = "문의사항 리스트 조회")
    @Secured({SecurityCode.USER, SecurityCode.ADMIN, SecurityCode.TRAINER})
    @GetMapping("/api/inquiry")
    public CommonResponse<PageResponse<List<InquiryGetResponse>>> getInquiryList(
        @AuthenticationPrincipal GrooveUserDetails grooveUserDetails, @RequestParam int page, @RequestParam int size) {

        var result = inquiryService.getList(grooveUserDetails.getUserId(), page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.map(InquiryGetResponse::new).toList(),
                result.hasNext()));
    }

    @Operation(summary = "문의사항 삭제")
    @Secured({SecurityCode.USER, SecurityCode.ADMIN, SecurityCode.TRAINER})
    @DeleteMapping("/api/inquiry/{inquiryId}")
    public CommonResponse<Void> deleteInquiry(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails, @PathVariable Long inquiryId) {
        Long inquiryUserId = inquiryService.get(inquiryId).getUserId();
        List<InquiryAttachmentCreateParam> attachmentCreateParamList = new ArrayList<>();
        if (grooveUserDetails.getUserId() == inquiryUserId) {
            inquiryService.delete(inquiryId);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        return CommonResponse.success();
    }

    @Operation(summary = "문의사항 수정")
    @Secured({SecurityCode.USER, SecurityCode.ADMIN, SecurityCode.TRAINER})
    @PutMapping(path = "/api/inquiry/{inquiryId}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<Void> updateInquiry(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                              @RequestPart InquiryUpdateRequest inquiryUpdateRequest, @PathVariable Long inquiryId,
                                              @RequestPart(required = false) List<MultipartFile> attachments) {
        List<InquiryAttachmentCreateParam> attachmentCreateParamList = new ArrayList<>();
        if (!attachments.isEmpty()) {
            attachmentCreateParamList = attachments.stream().map(param -> {
                try {
                    return new InquiryAttachmentCreateParam(param.getInputStream(), param.getName(), param.getSize(),
                        AttachmentType.INQUIRY_IMAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        inquiryService.update(inquiryId, grooveUserDetails.getUserId(), inquiryUpdateRequest.getTitle(),
            inquiryUpdateRequest.getContent(), attachmentCreateParamList);
        return CommonResponse.success();
    }
}
