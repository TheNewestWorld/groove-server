package org.bogus.groove.endpoint.voc;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.voc.VocService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VocController {
    private final VocService vocService;

    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping("/api/voc")
    public CommonResponse<Void> vocCreate(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                          @RequestBody VocRequestParam vocRequestParam) {
        vocService.create(grooveUserDetails.getUserId(), vocRequestParam.getContent());
        return CommonResponse.success();
    }
}
