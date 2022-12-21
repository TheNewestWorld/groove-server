package org.bogus.groove.endpoint.inquiry;

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.domain.attachment.InquiryAttachmentCreateParam;
import org.bogus.groove.domain.inquiry.InquiryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @Operation(summary = "문의사항 작성")
    @PostMapping("/api/inquiry")
    public CommonResponse<Void> createInquiry(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                              @RequestPart InquiryCreateRequest request,
                                              @RequestPart(required = false) List<MultipartFile> attachments) {
        List<InquiryAttachmentCreateParam> attachmentCreateParamList = new ArrayList<>();
        if (!attachments.isEmpty()) {
            attachmentCreateParamList = attachments.stream().map(param -> {
                try {
                    return new InquiryAttachmentCreateParam(param.getInputStream(), param.getName(), param.getSize(),
                        extCheck(param.getName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            inquiryService.create(grooveUserDetails.getUserId(), request.getTitle(), request.getContent(), attachmentCreateParamList);
        } else {
            inquiryService.create(grooveUserDetails.getUserId(), request.getTitle(), request.getContent(), attachmentCreateParamList);
        }
        return CommonResponse.success();
    }

    /*
    @Operation(summary = "문의사항 조회")
    @GetMapping("/api/inquiry/{inquiryId}")
    public CommonResponse<InquiryGetResponse> readInquiry(@PathVariable Long inquiryId){

    }
    */

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
