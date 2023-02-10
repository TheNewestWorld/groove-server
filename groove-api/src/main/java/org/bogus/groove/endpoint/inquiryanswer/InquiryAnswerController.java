package org.bogus.groove.endpoint.inquiryanswer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswerService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "inquiry-answer-controller")
@RestController
@RequiredArgsConstructor
public class InquiryAnswerController {

    private final InquiryAnswerService inquiryAnswerService;

    @Operation(summary = "문의사항 답글 작성")
    @Secured({SecurityCode.ADMIN})
    @PostMapping("/api/inquiry/{inquiryId}/inquiryanswer/")
    public CommonResponse<Void> createInquiryAnswer(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                                    @PathVariable Long inquiryId,
                                                    @RequestBody InquiryAnswerCreateRequest inquiryAnswerCreateRequest) {
        inquiryAnswerService.create(inquiryId, grooveUserDetails.getUserId(),
            inquiryAnswerCreateRequest.getTitle(),
            inquiryAnswerCreateRequest.getContent());
        return CommonResponse.success();
    }
}
